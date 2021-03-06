/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ivanovskij.controllers.servlets;

import com.github.ivanovskij.utils.AttributesToView;
import com.github.ivanovskij.utils.PageView;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author IOAdmin
 */
public class LocaleChangerController extends HttpServlet {
    
    private Locale currentLocale = new Locale("ru");
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getParameter("lang") != null && isLocaleAttribute(request)) {
                currentLocale = new Locale(request.getParameter("lang"));
                request.getSession().setAttribute(AttributesToView.ATTRIBUTE_MODEL_LOCALE, currentLocale);
                response.sendRedirect(PageView.PAGE_SUCCESS_MUSICS);
                return;
            }
        } catch (Exception ex) {
            // NOP
        }
        // FAIL
        response.sendRedirect(PageView.PAGE_ERROR);
    }

    private boolean isLocaleAttribute(HttpServletRequest request) {
        return request.getParameter("lang").equals("ru") || request.getParameter("lang").equals("en");
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
