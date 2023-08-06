/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import genre.GenreDAO;
import genre.GenreDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
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
public class MovieManagementController extends HttpServlet {

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
        String movieName = request.getParameter("name") == null ? "" : request.getParameter("name");
        String movieStatus_raw = request.getParameter("status");
        String movieGenre_raw = request.getParameter("genre");
        String moviePremiere_raw = request.getParameter("premiere");
        String _index = request.getParameter("index");
        
        int movieStatus, movieGenre;
        Date moviePremiere;

        try {
            movieStatus = ((movieStatus_raw == null || movieStatus_raw.equals(""))) ? 3 : Integer.parseInt(movieStatus_raw);
            movieGenre = ((movieGenre_raw == null || movieGenre_raw.equals(""))) ? 0 : Integer.parseInt(movieGenre_raw);
            moviePremiere = ((moviePremiere_raw == null || moviePremiere_raw.equals(""))) ? null : Date.valueOf(moviePremiere_raw);
            MovieDAO movieDAO = new MovieDAO();
            GenreDAO gerneDAO = new GenreDAO();
            List<GenreDTO> genreList = gerneDAO.genreList();
            
            int index;
            if(_index ==null || _index.isEmpty()) index =1;
            else index = Integer.parseInt(_index);
   
            List<MovieDTO> movieList = movieDAO.searchMovieForManagement(movieName, movieStatus, movieGenre, moviePremiere, index, 7);   
            int total = movieDAO.searchMovieForManagement(movieName, movieStatus, movieGenre, moviePremiere, index, -1).size();
            int totalPage = total/7;  //number of luckiness :3
            if(total % 7 !=0) totalPage++;
            
            request.setAttribute("genreList", genreList);
            request.setAttribute("mmList", movieList);
            request.setAttribute("index", index);
            request.setAttribute("totalPage", totalPage);
            //----send back these param to maintain searched webpage paging
            request.setAttribute("name", movieName);
            request.setAttribute("status", movieStatus);
            request.setAttribute("genre", movieGenre);
            request.setAttribute("premiere", moviePremiere);
            
            request.getRequestDispatcher("movie-management.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            System.out.println(e);
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
