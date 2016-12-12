/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.persistance;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author sivakrishna.k
 */
public interface IOperations<T extends Serializable> {
    
    //GET
    
    T findOne(final long id);
    
    List<T> findAll();
    
    Page<T> findPaginated(int page,int size );
    
    
    //write
    
    T create(final T entity);
    
    T update(final T entity);
    
    void delete(final T entity);
    
    void deleteById(final long id);
}
