/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.BlazarCryptoFile;
import com.blazartech.products.crypto.EncryptionScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author AAR1069
 */
@Configuration
public class BlazarCryptoFileConfiguration {
    
    @Bean
    public BlazarCryptoFile getBlazarCryptoFile() {
        return new BlazarCryptoFileImpl();
    }
    
    @Bean
    public EncryptionScheme encryptionScheme() {
        return new AESEncryptionScheme();
    }

}
