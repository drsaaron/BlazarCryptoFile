/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.EncryptionScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Use AES for encrypting and decrypting.  A key will be stored in a file.
 * Based on code at http://stackoverflow.com/a/9921369.
 * 
 * @author aar1069
 * @version $Id: AESEncryptionScheme.java 431 2016-11-07 17:13:47Z aar1069 $
 */

/* $Log$
 *******************************************************************************/
@Service
class AESEncryptionScheme extends EncryptionSchemeBaseImpl implements EncryptionScheme {

    private static final Logger logger = LoggerFactory.getLogger(AESEncryptionScheme.class);

    private static final String AES = "AES";
    private static final int KEY_SIZE = 128;
    
    @Override
    public String getCipherName() {
        return AES;
    }

    @Override
    public int getKeySize() {
        return KEY_SIZE;
    }
    
    
}
