/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto;

/**
 * interface for a generic encryption scheme.
 * 
 * @author aar1069
 * @version $Id: EncryptionScheme.java 431 2016-11-07 17:13:47Z aar1069 $
 */
public interface EncryptionScheme {
    
    /**
     * encrypt a string
     * @param s string to encrypt
     * @return encrypted string
     */
    String encrypt(String s);
    
    /**
     * decrypt a string
     * @param s the encrypted string to decrypt.  The string is assumed
     * to have been encrypted by the @see encrypt method.
     * @return the decrypted string
     */
    String decrypt(String s);
    
    /**
     * encrypt a generic byte array..
     * 
     * @param bytes
     * @return encrypted data
     */
    byte[] encrypt(byte[] bytes);
    
    /**
     * decrypte a byte stream
     * 
     * @param bytes
     * @return decrypted data
     */
    byte[] decrypt(byte[] bytes);
}
