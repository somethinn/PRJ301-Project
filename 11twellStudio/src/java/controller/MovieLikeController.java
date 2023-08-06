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
import javax.servlet.http.HttpSession;
import movie.MovieDAO;
import movie.MovieDTO;
import user.UserDTO;

/**
 *
 * @author ASUS
 */
public class MovieLikeController extends HttpServlet {

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
            out.println("<title>Servlet MovieLikeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MovieLikeController at " + request.getContextPath() + "</h1>");
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
        String movieID_raw = request.getParameter("movieID");
        int movieID;
        HttpSession session = request.getSession();
        UserDTO account = (UserDTO) session.getAttribute("account");
        try {
            if (account != null) {
                movieID = Integer.parseInt(movieID_raw);
                MovieDAO movieDAO = new MovieDAO();
                MovieDTO movie = movieDAO.getMovieByID(movieID);
                if (movieDAO.checkMovieNumberOfLikeDetailExist(movieID, account.getUserID()) == true) {
                    movie.setMovieNumberOfLike(movie.getMovieNumberOfLike() - 1);
                    movieDAO.deleteMovieNumberOfLikeDetail(movieID, account.getUserID());
                    movieDAO.updateMovieNumberOfLike(movie);


                } else {
                    movie.setMovieNumberOfLike(movie.getMovieNumberOfLike() + 1);
                    movieDAO.insertMovieNumberOfLikeDetail(movieID, account.getUserID());
                    movieDAO.updateMovieNumberOfLike(movie);


                }
                request.getRequestDispatcher("./movie-list").forward(request, response);

            } else {
                response.sendRedirect("login.jsp");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
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
