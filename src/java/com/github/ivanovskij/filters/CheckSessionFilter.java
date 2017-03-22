/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.filters;

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

    private static final String NOT_AUTHORIZE_PAGE = "pages/musics.jsp";
    
    private String div_user;
    private String div_auth;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        div_user = filterConfig.getInitParameter("displayUser");
        div_auth = filterConfig.getInitParameter("displayAuth");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session == null || session.isNew() || session.getAttribute("userAuth") == null) {
            session.setAttribute("displayUser", div_user);
            session.setAttribute("displayAuth", div_auth);
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NOP
    }
    
}
