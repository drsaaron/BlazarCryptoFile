/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto;

import java.util.Collection;

/**
 * This interface will define the capabilities of the crypto file, a file which
 * stores in a secure manner the password for a given combination of user ID and 
 * resource.  The resource can be any identifier, <em>e.g.</em> a server name or 
 * operating system.
 * 
 * @author aar1069
 */
public interface BlazarCryptoFile {
    
    /**
     * get the stored password for a given user ID and resource combination.
     * @param userID
     * @param resource
     * @return 
     */
    String getPassword(String userID, String resource);
    
    /**
     * update the password for a given user ID and resource combination.  If the
     * specified combination does not exist, it will be added.
     * 
     * @param userID
     * @param resource
     * @param password 
     */
    void updatePassword(String userID, String resource, String password);
    
    /**
     * get the collection of the key combinations found in the
     * file.
     * 
     * @return 
     */
    Collection<BlazarCryptoFileKey> getKeys();
}
