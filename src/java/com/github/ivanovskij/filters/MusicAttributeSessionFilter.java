/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.filters;

import com.github.ivanovskij.beans.Music;
import com.github.ivanovskij.dao.models.MusicsDAO;
import java.io.IOException;
import java.util.List;
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
public class MusicAttributeSessionFilter extends HttpBaseFilter {

    private final MusicsDAO musicDao = new MusicsDAO();
    private static final String ATTRIBUTE_MODEL_TO_VIEW = "listMusics";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // NOP
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session == null || session.isNew()) {
            List<Music> listMusics = musicDao.getMusicsByLimit(0, 15);
            session.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, listMusics);
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NOP
    }
    
}
