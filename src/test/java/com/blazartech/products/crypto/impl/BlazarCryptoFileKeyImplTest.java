/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.BlazarCryptoFileKey;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
public class BlazarCryptoFileKeyImplTest {
    
    private static final Logger logger = LoggerFactory.getLogger(BlazarCryptoFileKeyImplTest.class);
    
    @TestConfiguration
    public static class BlazarCryptoFileKeyImplTestConfiguration {
        
    }
    
    public BlazarCryptoFileKeyImplTest() {
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
     * Test of compareTo method, of class BlazarCryptoFileKeyImpl.
     */
    @Test
    public void testCompareTo() {
        logger.info("compareTo");
        
        BlazarCryptoFileKeyImpl k1 = new BlazarCryptoFileKeyImpl();
        k1.setResource("resource1");
        k1.setUserID("user1");
        
        BlazarCryptoFileKeyImpl k2 = new BlazarCryptoFileKeyImpl();
        k2.setResource("resource1");
        k2.setUserID("user2");
        
        BlazarCryptoFileKeyImpl k3 = new BlazarCryptoFileKeyImpl();
        k3.setResource("Resource1");
        k3.setUserID("user1");
        
        int k1k2 = k1.compareTo(k2);  // compare on user
        int k2k3 = k2.compareTo(k3);  // compare on user
        int k1k3 = k1.compareTo(k3);  // compare on resource as user is the same
        
        assertTrue(k1k2 < 0);
        assertTrue(k2k3 > 0);
        assertTrue(k1k3 > 0);
    }
    
}
