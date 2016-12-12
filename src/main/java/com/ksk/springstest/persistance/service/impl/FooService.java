/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.persistance.service.impl;

import com.ksk.springstest.persistance.dao.IFooDao;
import com.ksk.springstest.persistance.model.Foo;
import com.ksk.springstest.persistance.service.IFooService;
import com.ksk.springstest.persistance.service.common.AbstractService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sivakrishna.k
 */
public class FooService extends AbstractService<Foo> implements IFooService{

    @Autowired
    private IFooDao dao;

    public FooService() {
        super();
    }

    @Override
    protected PagingAndSortingRepository<Foo, Long> getDao(){
        return dao;
    }

    @Override
    public Foo retriveByName(String name) {
        return  dao.retriveByName(name);
    }
    
    //overriden with security
    
    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Foo> findAll(){
        return dao.findAll();
    }
    
}
