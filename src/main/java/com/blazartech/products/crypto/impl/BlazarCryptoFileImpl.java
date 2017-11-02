/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.EncryptionScheme;
import com.blazartech.products.crypto.BlazarCryptoFile;
import com.blazartech.products.crypto.BlazarCryptoFileKey;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import javax.inject.Provider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author aar1069
 * @version $Id: BlazarCryptoFileImpl.java 431 2016-11-07 17:13:47Z aar1069 $
 */

/* $Log$
 *******************************************************************************/

class BlazarCryptoFileImpl implements BlazarCryptoFile, InitializingBean {

    private static final Logger logger = Logger.getLogger(BlazarCryptoFileImpl.class);

    private final Map<BlazarCryptoFileKey, String> cryptoData = new TreeMap<>();

    @Value("${blazartech.crypto.file}")
    private String fileName;

    @Autowired
    private EncryptionScheme encryptionScheme;

    @Autowired
    private Provider<BlazarCryptoFileKeyImpl> keyProvider;

    private static final String delimiter = ":";

    private File getCryptoFile() throws IOException {
        File f = new File(fileName);
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }

    private void readCryptoFile() throws IOException {
        logger.info("reading crypto file " + fileName);
        File f = getCryptoFile();
        try (BufferedReader is = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = is.readLine()) != null) {
                String[] elements = line.split(delimiter);
                BlazarCryptoFileKeyImpl key = makeKey(elements[0], elements[1]);
                cryptoData.put(key, elements[2]);
            }
        }
    }

    private synchronized void writeCryptoFile() throws IOException {
        logger.info("writing crypto file " + fileName);
        File f = getCryptoFile();
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            getKeys().stream().map((key) -> {
                String password = cryptoData.get(key);
                String formattedData = key.getUserID() + delimiter + key.getResource() + delimiter + password;
                return formattedData;
            }).forEach((formattedData) -> {
                pw.println(formattedData);
            });
        }
    }

    private BlazarCryptoFileKeyImpl makeKey(String userID, String resource) {
        BlazarCryptoFileKeyImpl key = keyProvider.get();
        key.setUserID(userID);
        key.setResource(resource);
        return key;
    }

    private void loadCryptoFile() throws IOException {
        if (cryptoData.isEmpty()) {
            readCryptoFile();
        }
    }

    @Override
    public String getPassword(String userID, String resource) {
        logger.info("getting password for " + userID + "/" + resource);

        String encryptedPassword = cryptoData.get(makeKey(userID, resource));
        if (encryptedPassword == null) {
            throw new IllegalArgumentException("no crypto entry found for " + userID + "/" + resource);
        }
        String decryptedPassword = encryptionScheme.decrypt(encryptedPassword);
        return decryptedPassword;
    }

    @Override
    public void updatePassword(String userID, String resource, String password) {
        try {
            BlazarCryptoFileKeyImpl key = makeKey(userID, resource);
            cryptoData.put(key, encryptionScheme.encrypt(password));

            writeCryptoFile();
        } catch (IOException e) {
            logger.error("error updating crypto file: " + e.getMessage(), e);
            throw new RuntimeException("error updating crypto file: " + e.getMessage(), e);
        }
    }

    @Override
    public Collection<BlazarCryptoFileKey> getKeys() {
        return cryptoData.keySet();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadCryptoFile();
    }
}
