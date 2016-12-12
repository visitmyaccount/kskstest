/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.web.controller;

import com.ksk.springstest.persistance.service.IFooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;

/**
 *
 * @author sivakrishna.k
 */
@Controller
public class FooController {
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Autowired
    private IFooService service;

    public FooController() {
        super();
    }
    
    
    
}
