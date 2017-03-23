/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.controllers.servlets;

import com.github.ivanovskij.beans.Music;
import com.github.ivanovskij.dao.exception.DaoBusinessException;
import com.github.ivanovskij.dao.models.MusicsDAO;
import com.github.ivanovskij.enums.SearchType;
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
public class MusicsController extends HttpServlet {
    
    private final MusicsDAO musicsDAO = new MusicsDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            if (request.getParameter("genre_id") != null) {
                long genre_id = Long.valueOf(request.getParameter("genre_id"));
                List<Music> listMusics = musicsDAO.getMusicsByGenre(genre_id);
                request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_TO_VIEW_LIST_MUSICS, listMusics);
                //request.getRequestDispatcher(PAGE_SUCCESS).forward(request, response);
                response.sendRedirect(PageView.PAGE_SUCCESS_MUSICS);
                return;
            } else if (request.getParameter("letter") != null) {
                String letter = request.getParameter("letter");
                
                List<Music> listMusics;
                
                if (letter.equals("?")) {
                    listMusics = musicsDAO.selectAll();
                } else {
                    listMusics = musicsDAO.getMusicsByLetter(letter);
                }

                request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_TO_VIEW_LIST_MUSICS, listMusics);
                //request.getRequestDispatcher(PAGE_SUCCESS).forward(request, response);
                response.sendRedirect(PageView.PAGE_SUCCESS_MUSICS);
                return;
            } else if (request.getParameter("search") != null) {
                String searchStr = request.getParameter("search").trim();
                SearchType sType = SearchType.MUSIC_NAME;
                if (request.getParameter("searchType").equals("По альбому")) { // потом переделать для локалей, так как с англ языком работаь не будет
                    sType = SearchType.ALBUMS;
                }
                List<Music> listMusics = musicsDAO.getMusicsBySearch(searchStr, sType);
                request.getSession().setAttribute(AttributesToView.ATTRIBUTE_SEARCH_STRING, searchStr);
                request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_TO_VIEW_LIST_MUSICS, listMusics);
                //request.getRequestDispatcher(PAGE_SUCCESS).forward(request, response);
                response.sendRedirect(PageView.PAGE_SUCCESS_MUSICS);
                return;
            }
            
        } catch (NumberFormatException | IOException | DaoBusinessException e) {
            // NOP
        }
        // NO[
        // FAIL
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
