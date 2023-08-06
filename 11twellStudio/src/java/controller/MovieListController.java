package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movie.MovieDAO;
import movie.MovieDTO;


public class MovieListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MovieListNewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MovieListNewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        MovieDAO dao = new MovieDAO();

        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String paramIndex = request.getParameter("index") == null ? "1" : request.getParameter("index");
        String sortBy = request.getParameter("sortBy") == null ? "DESC" : request.getParameter("sortBy");
        String movieStatus_raw = (request.getParameter("status")== null  || request.getParameter("status").equals("")) ? "1" : request.getParameter("status");
        int movieStatus = Integer.parseInt(movieStatus_raw);
        List<MovieDTO> mlist = dao.searchMovieList(search, Integer.valueOf(paramIndex), 6, sortBy, movieStatus);
        int totalRows = dao.getTotalRowsCondition(search, movieStatus);
        int numberPage = (int) Math.ceil((double) totalRows / 6);

        try {
            request.setAttribute("mList", mlist);
            request.setAttribute("status", movieStatus);
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("sortBy", sortBy);
            request.getRequestDispatcher("movie-list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        MovieDAO dao = new MovieDAO();

        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        String paramIndex = request.getParameter("index") == null ? "1" : request.getParameter("index");
        String sortBy = request.getParameter("sortBy") == null ? "DESC" : request.getParameter("sortBy");
        String movieStatus_raw = (request.getParameter("status")== null  || request.getParameter("status").equals("")) ? "1" : request.getParameter("status");
        int movieStatus = Integer.parseInt(movieStatus_raw);
        List<MovieDTO> mlist = dao.searchMovieList(search, Integer.valueOf(paramIndex), 6, sortBy, movieStatus);
        int totalRows = dao.getTotalRowsCondition(search, movieStatus);
        int numberPage = (int) Math.ceil((double) totalRows / 6);

        try {
            request.setAttribute("mList", mlist);
            request.setAttribute("status", movieStatus);
            request.setAttribute("numberPage", numberPage);
            request.setAttribute("sortBy", sortBy);
            request.getRequestDispatcher("movie-list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
