/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author sivakrishna.k
 */
@Configuration
@ImportResource({"classpath:webSecurityConfig.xml"})
public class SecSecurityConfig {

    public SecSecurityConfig() {
        super();
    }
    
}
