/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import com.blazartech.products.crypto.BlazarCryptoFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author AAR1069
 */
@Configuration
@PropertySource("file:${user.home}/.blazartech/crypto.properties")
public class BlazarCryptoFileConfiguration {
    
    @Bean
    public BlazarCryptoFile getBlazarCryptoFile() {
        return new BlazarCryptoFileImpl();
    }
}
