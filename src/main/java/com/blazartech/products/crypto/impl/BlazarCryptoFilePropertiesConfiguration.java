/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.crypto.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author scott
 */
@Configuration
@PropertySource("file:${user.home}/.blazartech/crypto.properties")
public class BlazarCryptoFilePropertiesConfiguration {
    
}
