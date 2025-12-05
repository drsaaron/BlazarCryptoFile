/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.BlazarCryptoFileKey;
import java.util.Collection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author AAR1069
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    BlazarCryptoFileImplTest.BlazarCryptoFileImplTestConfig.class,
    BlazarCryptoFileConfiguration.class 
})
public class BlazarCryptoFileImplTest {
    
    private static final Logger logger = LoggerFactory.getLogger(BlazarCryptoFileImplTest.class);
    
    @Configuration
    @PropertySource("classpath:test.properties")
    static class BlazarCryptoFileImplTestConfig {
        
    }
    
    @Autowired
    private BlazarCryptoFileImpl cryptoFile;
    
    public BlazarCryptoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
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
