/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.controllers.servlets;

import com.github.ivanovskij.beans.Music;
import com.github.ivanovskij.dao.models.MusicsDAO;
import com.github.ivanovskij.enums.SearchType;
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
    
    private static final String ATTRIBUTE_SEARCH_STRING = "searchStr";
    
    private static final String ATTRIBUTE_MODEL_TO_VIEW = "listMusics";
    private static final String PAGE_SUCCESS = "pages/musics.jsp";
    private static final String PAGE_ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getParameter("genre_id") != null) {
                long genre_id = Long.valueOf(request.getParameter("genre_id"));
                final MusicsDAO musicsDAO = new MusicsDAO();
                List<Music> listMusics = musicsDAO.getMusicsByGenre(genre_id);
                request.getSession().setAttribute(ATTRIBUTE_MODEL_TO_VIEW, listMusics);
                //request.getRequestDispatcher(PAGE_SUCCESS).forward(request, response);
                response.sendRedirect(PAGE_SUCCESS);
                return;
            } else if (request.getParameter("letter") != null) {
                String letter = request.getParameter("letter");
                
                final MusicsDAO musicsDAO = new MusicsDAO();
                List<Music> listMusics;
                
                if (letter.equals("?")) {
                    listMusics = musicsDAO.getAllMusics();
                } else {
                    listMusics = musicsDAO.getMusicsByLetter(letter);
                }

                request.getSession().setAttribute(ATTRIBUTE_MODEL_TO_VIEW, listMusics);
                //request.getRequestDispatcher(PAGE_SUCCESS).forward(request, response);
                response.sendRedirect(PAGE_SUCCESS);
                return;
            } else if (request.getParameter("search") != null) {
                String searchStr = request.getParameter("search").trim();
                SearchType sType = SearchType.MUSIC_NAME;
                if (request.getParameter("searchType").equals("По альбому")) { // потом переделать для локалей, так как с англ языком работаь не будет
                    sType = SearchType.ALBUMS;
                }
                final MusicsDAO musicsDAO = new MusicsDAO();
                List<Music> listMusics = musicsDAO.getMusicsBySearch(searchStr, sType);
                request.getSession().setAttribute(ATTRIBUTE_SEARCH_STRING, searchStr);
                request.getSession().setAttribute(ATTRIBUTE_MODEL_TO_VIEW, listMusics);
                //request.getRequestDispatcher(PAGE_SUCCESS).forward(request, response);
                response.sendRedirect(PAGE_SUCCESS);
                return;
            }
            
        } catch (NumberFormatException | IOException e) {
            // NOP
        }
        // FAIL
        response.sendRedirect(PAGE_ERROR);
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
