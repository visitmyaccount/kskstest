/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.persistance.service.common;

import com.google.common.collect.Lists;
import com.ksk.springstest.persistance.IOperations;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sivakrishna.k
 */
@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

    
    protected abstract PagingAndSortingRepository<T, Long> getDao();
    
    @Override
    @Transactional(readOnly = true)
    public T findOne(final long id){
        return getDao().findOne(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(){
        return Lists.newArrayList(getDao().findAll()); 
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Page<T> findPaginated(final int page, final int size){
        return getDao().findAll(new PageRequest(page, size));
    }

    @Override
    public T create(final T entity){
        return getDao().save(entity);
    }
    
    @Override
    public T update(final T entity){
        return getDao().save(entity);
    }
    
    @Override
    public void delete(final T entity){
        getDao().delete(entity);
    }
    
    @Override
    public void deleteById(final long id){
        getDao().delete(id);
    }
    
}
