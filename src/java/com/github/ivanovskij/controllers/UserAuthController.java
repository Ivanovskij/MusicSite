/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.controllers;

import com.github.ivanovskij.beans.User;
import com.github.ivanovskij.dao.models.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IOAdmin
 */
public class UserAuthController extends HttpServlet {
    
    private static final String DISPLAY_DIV_AUTH_NONE = "none";
    private static final String DISPLAY_DIV_AUTH_BLOCK = "block";
    private static final String ATTRIBUTE_MODEL_DIV_AUTH = "displayAuth";
    private static final String ATTRIBUTE_MODEL_DIV_USER = "displayUser";
    
    private static final String PAGE_SUCCESS = "pages/musics.jsp";
    private static final String PAGE_ERROR = "error.jsp";
    
    private static final String ATTRIBUTE_MODEL_LOCALE = "currentLocale";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try {
            // LOGIN
            if (request.getParameter("userlogin") != null 
                    &&
                    request.getParameter("userpass") != null) {
                String username = request.getParameter("userlogin");
                String userpass = request.getParameter("userpass");
                        
                UserDAO userDAO = new UserDAO();
                User user = userDAO.login(username, userpass);
                
                if (user != null) {
                    request.getSession().setAttribute("userAuth", user);
                    request.getSession().setAttribute(ATTRIBUTE_MODEL_LOCALE, user.getLang());
                    request.getSession().setAttribute(ATTRIBUTE_MODEL_DIV_AUTH, DISPLAY_DIV_AUTH_NONE);
                    request.getSession().setAttribute(ATTRIBUTE_MODEL_DIV_USER, DISPLAY_DIV_AUTH_BLOCK);
                    response.sendRedirect(request.getContextPath() + "/" + PAGE_SUCCESS);
                    return;
                } else {
                    request.getSession().setAttribute(ATTRIBUTE_MODEL_DIV_AUTH, DISPLAY_DIV_AUTH_BLOCK);
                    request.getSession().setAttribute(ATTRIBUTE_MODEL_DIV_USER, DISPLAY_DIV_AUTH_NONE);
                    // return; не нужно, чтобы кинулась ошибка: response.sendRedirect(PAGE_ERROR);
                }
            }
            
            // LOGOUT
            if (request.getParameter("logout") != null) {
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/" + PAGE_SUCCESS);
                return;
            }
        } catch (IOException ex) {
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
