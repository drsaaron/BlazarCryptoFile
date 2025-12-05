/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author AAR1069
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AESEncryptionSchemeTest.AESEncryptionSchemeTestConfig.class )
public class AESEncryptionSchemeTest {
    
    private static final Logger logger = LoggerFactory.getLogger(AESEncryptionSchemeTest.class);
    
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
