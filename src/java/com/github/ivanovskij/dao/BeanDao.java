/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao;

import com.github.ivanovskij.dao.exception.DaoBusinessException;
import com.github.ivanovskij.dao.exception.NoSuchEntityException;
import java.util.List;


/**
 *
 * @author IOAdmin
 * @param <T>
 */
public interface BeanDao<T> {
    
    T selectById(long id) throws NoSuchEntityException, DaoBusinessException;
    
    List<T> selectAll() throws DaoBusinessException;
}
