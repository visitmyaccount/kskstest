/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.persistance.service;

import com.ksk.springstest.persistance.IOperations;
import com.ksk.springstest.persistance.model.Foo;

/**
 *
 * @author sivakrishna.k
 */
public interface IFooService extends IOperations<Foo>{
    
    Foo retriveByName(String name);
}
