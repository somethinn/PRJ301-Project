/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Thanh
 */
@WebServlet(name = "CheckController", urlPatterns = {"/check"})
public class CheckController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action.equals("add")) {
            request.getRequestDispatcher("add-movie").forward(request, response);
        }
        
        if(action.equals("delete")) {
            request.getRequestDispatcher("delete-movie").forward(request, response);
        }
        
        if(action.equals("update")) {
            request.getRequestDispatcher("update-movie").forward(request, response);
        } 
        
        if(action.equals("switch")) {
            request.getRequestDispatcher("switch-movie-status").forward(request, response);
        }
        
        if(action.equals("add-post")) {
            request.getRequestDispatcher("add-post").forward(request, response);
        }
        
        if(action.equals("delete-post")) {
            request.getRequestDispatcher("delete-post").forward(request, response);
        }
        
        if(action.equals("switch-post")) {
            request.getRequestDispatcher("switch-post-status").forward(request, response);
        }
        
        if(action.equals("update-post")) {
            request.getRequestDispatcher("update-post").forward(request, response);
        }
        
        if(action.equals("switch-movie-slider-status")) {
            request.getRequestDispatcher("switch-movie-slider-status").forward(request, response);
        }
        
        if(action.equals("update-slider")) {
            request.getRequestDispatcher("update-slider").forward(request, response);
        }
        
        if(action.equals("add-show")) {
            request.getRequestDispatcher("add-show").forward(request, response);
        }
        
        
         if(action.equals("delete-show")) {
            request.getRequestDispatcher("delete-show").forward(request, response);
        }
         
         if(action.equals("switch-show")) {
            request.getRequestDispatcher("switch-show-status").forward(request, response);
        }
         
         if(action.equals("update-show")) {
            request.getRequestDispatcher("update-show").forward(request, response);
        }
         
        if (action.equals("delete-order")) {
            request.getRequestDispatcher("delete-order").forward(request, response);
        }
        
        if(action.equals("add-promo")) {
            request.getRequestDispatcher("add-promo").forward(request, response);
        }
        
        
        if(action.equals("switch-promo")) {
            request.getRequestDispatcher("switch-promo").forward(request, response);
        }
        
        if(action.equals("delete-promo")) {
            request.getRequestDispatcher("delete-promo").forward(request, response);
        }
        
         if(action.equals("update-promo")) {
            request.getRequestDispatcher("update-promo").forward(request, response);
        }
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
