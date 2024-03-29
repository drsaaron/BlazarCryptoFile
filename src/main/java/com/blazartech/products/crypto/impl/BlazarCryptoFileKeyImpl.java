/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.BlazarCryptoFileKey;

/**
 * define an object to combine the user ID and resource.
 * 
 * @author aar1069
 * @version $Id: BlazarCryptoFileKeyImpl.java 431 2016-11-07 17:13:47Z aar1069 $
 */

/* $Log$
 *******************************************************************************/

public class BlazarCryptoFileKeyImpl implements BlazarCryptoFileKey {

    private String userID;
    private String resource;

    @Override
    public String getResource() {
        return resource;
    }

    public void setResource(String server) {
        this.resource = server;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    @Override
    public int compareTo(BlazarCryptoFileKey o) {
        if (getUserID().compareTo(o.getUserID()) == 0) {
            return getResource().compareTo(o.getResource());
        } else {
            return getUserID().compareTo(o.getUserID());
        }
    }
    
}
