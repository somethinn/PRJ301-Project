/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movie.MovieDAO;
import movie.MovieDTO;

/**
 *
 * @author Nguyen Thanh
 */
public class UpdateSliderController extends HttpServlet {

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
            out.println("<title>Servlet UpdateSliderController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSliderController at " + request.getContextPath() + "</h1>");
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
        String movieID_raw = request.getParameter("id");
        
        int movieID;
        
        try {
            movieID = Integer.parseInt(movieID_raw);
            MovieDAO movieDAO = new MovieDAO();
            MovieDTO movie = movieDAO.getMovieByID(movieID);
            
            request.setAttribute("movieList", movie);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("edit-slider-management.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        String sliderImage = request.getParameter("slider-image");
        String movieID_raw = request.getParameter("movie-id");
        
        int movieID;
        
        try {
            movieID = Integer.parseInt(movieID_raw);
            MovieDAO movieDAO = new MovieDAO();
            MovieDTO movie = new MovieDTO();
            movie.setMovieID(movieID);
            movie.setMovieImageSlide(sliderImage);
            movieDAO.updateSliderImage(movie);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        response.sendRedirect("slider-management");
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
