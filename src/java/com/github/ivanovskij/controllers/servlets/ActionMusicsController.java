/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.controllers.servlets;

import com.github.ivanovskij.beans.Music;
import com.github.ivanovskij.dao.exception.DaoBusinessException;
import com.github.ivanovskij.dao.exception.NoSuchEntityException;
import com.github.ivanovskij.dao.models.AlbumsDAO;
import com.github.ivanovskij.dao.models.GenresDAO;
import com.github.ivanovskij.dao.models.MusicsDAO;
import com.github.ivanovskij.utils.AttributesToView;
import com.github.ivanovskij.utils.PageView;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IOAdmin
 */
public class ActionMusicsController extends HttpServlet {
    
    private final GenresDAO genreDao = new GenresDAO();
    private final AlbumsDAO albumDao = new AlbumsDAO();
    private final MusicsDAO musicDao = new MusicsDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        try {
            if (request.getParameter("add") != null) {
                String name = request.getParameter("name");
                String album = request.getParameter("album");
                String genre = request.getParameter("genre");
                
                long idAlbum = albumDao.selectIdByName(album);
                long idGenre = genreDao.selectIdByName(genre);
                
                musicDao.addMusic(name, idAlbum, idGenre);

                int countAllMusics = musicDao.getCountAllMusics();
                int countAllAlbums = albumDao.getCountAllAlbums();
                request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_VIEW_COUNT_MUSICS, countAllMusics);
                request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_VIEW_COUNT_ALBUMS, countAllAlbums);
                
                List<Music> listMusics = musicDao.getMusicsByLimit(0, 15);
                request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_TO_VIEW_LIST_MUSICS, listMusics);
                
                response.sendRedirect(PageView.PAGE_SUCCESS_ADD);
                return;
            } else if (request.getParameter("delete") != null) {
                String idMusicStr = request.getParameter("id");
                if (idMusicStr != null) {
                    long id = Long.valueOf(idMusicStr);
                    musicDao.delMusic(id);
                    List<Music> listMusics = musicDao.getMusicsByLimit(0, 15);
                    request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_TO_VIEW_LIST_MUSICS, listMusics);
                    response.sendRedirect(PageView.PAGE_SUCCESS_DELETE);
                    return;
                } 
            } else if (request.getParameter("getMusic") != null) {
                String idMusicStr = request.getParameter("id");
                if (idMusicStr != null) {
                    long id = Long.valueOf(idMusicStr);
                    Music music = (Music) musicDao.selectById(id);
                    request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_TO_VIEW_UPDATE, music);
                    response.sendRedirect(PageView.PAGE_SUCCESS_UPDATE_POPUP);
                    return;
                } 
            } else if (request.getParameter("update") != null) {
                String idMusicStr = request.getParameter("idMusic");
                long id = Long.valueOf(idMusicStr);
                String name = request.getParameter("name");
                String album = request.getParameter("album");
                String genre = request.getParameter("genre");

                long idAlbum = albumDao.selectIdByName(album);
                long idGenre = genreDao.selectIdByName(genre);

                musicDao.updateMusic(id ,name, idAlbum, idGenre);

                List<Music> listMusics = musicDao.getMusicsByLimit(0, 15);
                request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_TO_VIEW_LIST_MUSICS, listMusics);

                response.sendRedirect(PageView.PAGE_SUCCESS_UPDATE);
                return;
            }
        } catch (NoSuchEntityException n) {
            // NOP
        } catch (DaoBusinessException d) {
            // NOP
        }
        
        //FAIL
        response.sendRedirect(PageView.PAGE_ERROR);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
