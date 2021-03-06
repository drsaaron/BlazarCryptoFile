/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto;

import java.io.Serializable;

/**
 * interface for key data in the crypto file.  The key for the crypto file is
 * the combination of userID and resource ID.
 * 
 * @author aar1069
 * @version $Id: BlazarCryptoFileKey.java 431 2016-11-07 17:13:47Z aar1069 $
 */
public interface BlazarCryptoFileKey extends Comparable<BlazarCryptoFileKey>, Serializable {

    /**
     * get the resource ID
     * 
     * @return 
     */
    String getResource();

    /**
     * get the user ID.
     * @return 
     */
    String getUserID();
    
}
