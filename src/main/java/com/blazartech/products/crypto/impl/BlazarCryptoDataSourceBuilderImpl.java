/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.BlazarCryptoDataSource;
import com.blazartech.products.crypto.BlazarCryptoDataSourceBuilder;
import com.blazartech.products.crypto.BlazarCryptoFile;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
class BlazarCryptoDataSourceBuilderImpl implements BlazarCryptoDataSourceBuilder {

    private static final Logger logger = Logger.getLogger(BlazarCryptoDataSourceBuilderImpl.class);
    
    @Autowired
    private BlazarCryptoFile cryptoFile;
    
    @Override
    public DataSource getDataSource(String userID, String resourceID, String url) {
        logger.info("getting connection for " + url);
        
        BlazarCryptoDataSource ds = new BlazarCryptoDataSource();
        ds.setCryptoFile(cryptoFile);
        ds.setUserID(userID);
        ds.setResource(resourceID);
        
        DriverManagerDataSource rawDS = new DriverManagerDataSource();
        rawDS.setUrl(url);
        
        ds.setRawDataSource(rawDS);
        
        return ds;
    }
    
}
