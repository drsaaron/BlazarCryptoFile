/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author AAR1069
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AESEncryptionSchemeTest.AESEncryptionSchemeTestConfig.class )
public class AESEncryptionSchemeTest {
    
    private static final Logger logger = Logger.getLogger(AESEncryptionSchemeTest.class);
    
    @Configuration
    @PropertySource("classpath:test.properties")
    static class AESEncryptionSchemeTestConfig {
        
        @Bean
        public AESEncryptionScheme getAESEncryptionScheme() {
            return new AESEncryptionScheme();
        }
    }
    
    @Autowired
    private AESEncryptionScheme encryptionScheme;
    
    @Value("${test.text}")
    private String testText;
    
    @Value("${test.encryptedText}")
    private String testEncryptedText;
    
    public AESEncryptionSchemeTest() {
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

    /**
     * Test of getKeySize method, of class AESEncryptionScheme.
     */
    @Test
    public void testEncrypt() {
        logger.info("testEncrypt");

        String expResult = testEncryptedText;
        String result = encryptionScheme.encrypt(testText);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDecrypt() {
        logger.info("testDecrypt");
        
        String expResult = testText;
        String result = encryptionScheme.decrypt(testEncryptedText);
        assertEquals(expResult, result);
    }
}
