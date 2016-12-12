/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.persistance.dao;

import com.ksk.springstest.persistance.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sivakrishna.k
 */
public interface IFooDao extends JpaRepository<Foo, Long>, JpaSpecificationExecutor<Foo>{
    
    @Query("SELECT f FROM Foo f WHERE LOWER(f.name) = LOWER(:name) ")
    Foo retriveByName(@Param("name") String name);
}
