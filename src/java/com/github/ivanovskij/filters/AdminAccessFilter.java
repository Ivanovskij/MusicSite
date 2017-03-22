/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.filters;

import com.github.ivanovskij.beans.User;
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
public class AdminAccessFilter extends HttpBaseFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        
        response.setCharacterEncoding("utf-8");
        if (session.getAttribute("userAuth") != null) {
            User user = (User) session.getAttribute("userAuth");
            if (!user.getName().equals("root")) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden, доступ запрещен");
            }
        } else {
            // если session.getAttribute("userAuth") == null => его не существует
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden, доступ запрещен");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
