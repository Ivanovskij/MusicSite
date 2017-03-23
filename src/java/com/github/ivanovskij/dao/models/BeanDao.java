/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.dao.models;

import com.github.ivanovskij.dao.exception.DaoBusinessException;
import com.github.ivanovskij.dao.exception.NoSuchEntityException;
import java.util.List;


/**
 *
 * @author IOAdmin
 * @param <T>
 */
public abstract class BeanDao<T> {
    
    public abstract T selectById(long id) throws NoSuchEntityException, DaoBusinessException;
    
    public abstract long selectIdByName(String name) throws NoSuchEntityException, DaoBusinessException;
    
    public abstract List<T> selectExecute(String query) throws DaoBusinessException;
    
    public abstract List<T> selectAll() throws DaoBusinessException;
}
