/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.BlazarCryptoFileKey;
import com.blazartech.products.crypto.EncryptionScheme;
import java.util.Collection;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author AAR1069
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BlazarCryptoFileImplTest.BlazarCryptoFileImplTestConfig.class )
public class BlazarCryptoFileImplTest {
    
    private static final Logger logger = Logger.getLogger(BlazarCryptoFileImplTest.class);
    
    @Configuration
    @PropertySource("classpath:test.properties")
    static class BlazarCryptoFileImplTestConfig {
        
        @Bean
        public EncryptionScheme getEncryptionScheme() {
            return new AESEncryptionScheme();
        }
        
        @Bean
        public BlazarCryptoFileImpl getCryptoFile() {
            return new BlazarCryptoFileImpl();
        }
        
        @Bean
        @Scope("prototype")
        public BlazarCryptoFileKeyImpl getCryptoFileKey() {
            return new BlazarCryptoFileKeyImpl();
        }
    }
    
    @Autowired
    private BlazarCryptoFileImpl cryptoFile;
    
    public BlazarCryptoFileImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    private static final String USER_ID = "drsaaron";
    private static final String RESOURCE_ID = "jUnit";
    private static final String PASSWORD = "MyPassword";
    
    /**
     * Test of getPassword method, of class BlazarCryptoFileImpl.
     */
    @Test
    public void testGetPassword() {
        logger.info("testGetPassword");

        String expResult = PASSWORD;
        String result = cryptoFile.getPassword(USER_ID, RESOURCE_ID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getKeys method, of class BlazarCryptoFileImpl.
     */
    @Test
    public void testGetKeys() {
        logger.info("testGetKeys");
        
        Collection<BlazarCryptoFileKey> result = cryptoFile.getKeys();
        assertEquals(1, result.size());
        BlazarCryptoFileKey key = result.iterator().next();
        assertEquals(key.getUserID(), USER_ID);
        assertEquals(key.getResource(), RESOURCE_ID);
    }
    
}
