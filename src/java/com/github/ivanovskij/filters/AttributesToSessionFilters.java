/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IOAdmin
 */
public class AttributesToSessionFilters implements Filter {

    private static final String DISPLAY_DIV_AUTH_NONE = "none";
    private static final String DISPLAY_DIV_AUTH_BLOCK = "block";
    private static final String ATTRIBUTE_MODEL_DIV_AUTH = "displayAuth";
    private static final String ATTRIBUTE_MODEL_DIV_USER = "displayUser";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        if (session == null || session.isNew()) {
            session.setAttribute(ATTRIBUTE_MODEL_DIV_USER, DISPLAY_DIV_AUTH_NONE);
            session.setAttribute(ATTRIBUTE_MODEL_DIV_AUTH, DISPLAY_DIV_AUTH_BLOCK);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NOP
    } 
}
