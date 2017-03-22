/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.filters;

import com.github.ivanovskij.dao.models.AlbumsDAO;
import com.github.ivanovskij.dao.models.MusicsDAO;
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
public class AdminAttributeSessionFilter extends HttpBaseFilter {

    private final MusicsDAO musicDao = new MusicsDAO();
    private final AlbumsDAO albumDao = new AlbumsDAO();
    
    private static final String ATTRIBUTE_MODEL_COUNT_MUSICS = "countAllMusics";
    private static final String ATTRIBUTE_MODEL_COUNT_ALBUMS = "countAllAlbums";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // NOP
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        
        response.setCharacterEncoding("utf-8");
        
        int countAllMusics = musicDao.getCountAllMusics();
        int countAllAlbums = albumDao.getCountAllAlbums();
        session.setAttribute(ATTRIBUTE_MODEL_COUNT_MUSICS, countAllMusics);
        session.setAttribute(ATTRIBUTE_MODEL_COUNT_ALBUMS, countAllAlbums);
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NOP
    }
    
}
