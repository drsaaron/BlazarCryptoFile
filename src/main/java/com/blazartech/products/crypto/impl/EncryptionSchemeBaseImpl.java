/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.EncryptionScheme;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * Base class for encryption schemes that leverage the javax.crypto ciphers.  The
 * framework should be the same regardless of the specific cipher used, therefore
 * this class will implement the core framework of a key file, a data store file
 * with encrypted passwords, conversion to and from hex strings, etc. and derived
 * classes will specify the specific cipher to be used.
 * 
 * Based on code at http://stackoverflow.com/a/9921369.
 * 
 * @author aar1069
 * @version $Id: EncryptionSchemeBaseImpl.java 431 2016-11-07 17:13:47Z aar1069 $
 */
public abstract class EncryptionSchemeBaseImpl implements EncryptionScheme, InitializingBean {
    
    private static final Logger logger = Logger.getLogger(EncryptionSchemeBaseImpl.class);
    
    private File keyFile;
    
    @Value("${blazartech.crypto.keyFile}")
    private String keyFileName;

    @Override
    public void afterPropertiesSet() throws Exception {
        keyFile = new File(keyFileName);
    }

    /**
     * specify the particular cipher name used by the scheme.
     * 
     * @return 
     */
    public abstract String getCipherName();
    
    private Cipher cipher;
    
    public Cipher getCipher() {
        if (cipher == null) {
            try {
                cipher = Cipher.getInstance(getCipherName());
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new RuntimeException("error getting cipher: " + e.getMessage(), e);
            }
        }
        return cipher;
    }

    @Override
    public String encrypt(String s) {
        return byteArrayToHexString(encrypt(s.getBytes()));
    }
    
    /**
     * provide the size of the key used by the particular scheme.
     * @return 
     */
    public abstract int getKeySize();
    
    @Override
    public synchronized byte[] encrypt(byte[] s) {
        try {
            if (!keyFile.exists()) {
                logger.info("creating key file.");

                KeyGenerator keyGen = KeyGenerator.getInstance(getCipherName());
                keyGen.init(getKeySize());
                SecretKey sk = keyGen.generateKey();
                FileWriter fw = new FileWriter(keyFile);
                fw.write(byteArrayToHexString(sk.getEncoded()));
                fw.flush();
            }

            SecretKeySpec sks = getSecretKeySpec(keyFile);
            getCipher().init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
            byte[] encrypted = cipher.doFinal(s);

            return encrypted;

        } catch (NoSuchAlgorithmException | IOException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e);
            throw new RuntimeException("error encrypting: " + e.getMessage(), e);
        }
    }

    /**
     * decrypt a string.  Note that this assumes the string is a hex string
     * created by @see encrypt.
     * 
     * @param s
     * @return 
     */
    @Override
    public synchronized String decrypt(String s) {
        return new String(decrypt(hexStringToByteArray(s)));
    }

    @Override
    public byte[] decrypt(byte[] bytes) {
        try {
            SecretKeySpec sks = getSecretKeySpec(keyFile);
            getCipher().init(Cipher.DECRYPT_MODE, sks);
            byte[] decrypted = cipher.doFinal(bytes);
            return decrypted;
        } catch (NoSuchAlgorithmException | IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e);
            throw new RuntimeException("error decrypting: " + e.getMessage(), e);
        }
    }
    
    

    private SecretKeySpec keySpec = null;
    
    private synchronized SecretKeySpec getSecretKeySpec(File keyFile) throws NoSuchAlgorithmException, IOException {
        if (keySpec == null) {
            byte[] key = readKeyFile(keyFile);
            keySpec = new SecretKeySpec(key, getCipherName());
        }
        return keySpec;
    }

    private byte[] readKeyFile(File keyFile) throws FileNotFoundException {
        String keyValue;
        Scanner scanner = new Scanner(keyFile).useDelimiter("\\Z");
        keyValue = scanner.next();
        return hexStringToByteArray(keyValue);
    }

    private String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }
}
