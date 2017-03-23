/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.filters;

import com.github.ivanovskij.utils.AttributesToView;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author IOAdmin
 */
public class CheckSessionFilter extends HttpBaseFilter {
    
    private String div_user;
    private String div_auth;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        div_user = filterConfig.getInitParameter(AttributesToView.ATTRIBUTE_MODEL_DIV_USER);
        div_auth = filterConfig.getInitParameter(AttributesToView.ATTRIBUTE_MODEL_DIV_AUTH);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session == null || session.isNew() || session.getAttribute("userAuth") == null) {
            session.setAttribute(AttributesToView.ATTRIBUTE_MODEL_DIV_USER, div_user);
            session.setAttribute(AttributesToView.ATTRIBUTE_MODEL_DIV_AUTH, div_auth);
        }
        
        // если пользователь зашел
        if (session.getAttribute("userAuth") != null) {
            session.setAttribute(AttributesToView.ATTRIBUTE_MODEL_DIV_USER, AttributesToView.DISPLAY_DIV_BLOCK);
            session.setAttribute(AttributesToView.ATTRIBUTE_MODEL_DIV_AUTH, AttributesToView.DISPLAY_DIV_NONE);
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NOP
    }
    
}
