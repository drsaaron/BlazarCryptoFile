/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto;

import javax.sql.DataSource;

/**
 *
 * @author aar1069
 */
public interface BlazarCryptoDataSourceBuilder {
    
    public DataSource getDataSource(String userID, String resourceID, String url);
}
