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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movie.MovieDAO;
import movie.MovieDTO;

/**
 *
 * @author Nguyen Thanh
 */
public class UpdateMovieController extends HttpServlet {

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
            out.println("<title>Servlet UpdateMovieController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMovieController at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        int id;
        MovieDAO movieDAO = new MovieDAO();
        GenreDAO genreDAO = new GenreDAO();
        try {
            id = Integer.parseInt(id_raw);
            MovieDTO movie = movieDAO.getMovieByID(id);
            List<GenreDTO> genre = genreDAO.genreList();
            request.setAttribute("movie", movie);
            request.setAttribute("genreList", genre);
            request.getRequestDispatcher("edit-movie-management.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
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
        request.setCharacterEncoding("UTF-8");
        MovieDAO movieDAO = new MovieDAO();
        String movieID_raw = request.getParameter("movie-id");
        String movieTittle = request.getParameter("movie-tittle");
        String movieGenre_raw = request.getParameter("movie-genre");
        String movieImage = request.getParameter("movie-image");
        String moviePremiere_raw = request.getParameter("movie-premiere");
        String movieDirector = request.getParameter("movie-director");
        String movieTime_raw = request.getParameter("movie-time");
        String movieSlide = request.getParameter("movie-slide");
        String movieDes = request.getParameter("movie-des");
        String movieTrailer = request.getParameter("movie-trailer");
        String movieStatus_raw = request.getParameter("movie-status");

        int movieGenre;
        Date moviePremiere;
        int movieStatus;
        int movieTime;
        int movieID;

        try {
            movieID = Integer.parseInt(movieID_raw);
            movieGenre = Integer.parseInt(movieGenre_raw);
            moviePremiere = Date.valueOf(moviePremiere_raw);
            movieStatus = Integer.parseInt(movieStatus_raw);
            movieTime = Integer.parseInt(movieTime_raw);
            MovieDTO movie = new MovieDTO();
            movie.setMovieTitle(movieTittle);
            movie.setMovieImage(movieImage);
            movie.setMoviePremiere(moviePremiere);
            movie.setMovieDirector(movieDirector);
            movie.setMovieTime(movieTime);
            movie.setMovieImageSlide(movieSlide);
            movie.setMovieDescription(movieDes);
            if (movieTrailer.contains("v=")) {
                String[] movieTrailerSplit = movieTrailer.split("v=");
                movie.setMovieTrailerLink("https://youtube.com/embed/" + movieTrailerSplit[movieTrailerSplit.length - 1]);

            } else {
                movie.setMovieTrailerLink(movieTrailer);

            }
            movie.setMovieStatus(movieStatus);
            movie.setMovieID(movieID);

            movieDAO.updateMovie(movie, movieGenre);
        } catch (Exception e) {
            System.out.println(e);
        }
        response.sendRedirect("movie-management");
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
