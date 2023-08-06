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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movie.MovieDAO;
import movie.MovieDTO;
import show.ShowDAO;
import show.ShowDTO;

/**
 *
 * @author ASUS
 */
public class ShowManagement extends HttpServlet {

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
        
            /* TODO output your page here. You may use following sample code. */
            
            ShowDAO dao = new ShowDAO();
            
            String name = request.getParameter("name");
            String _status = request.getParameter("status")== null ? "3" : request.getParameter("status");
            String _date = request.getParameter("date");
            String _index = (request.getParameter("index") == null )? "1" : request.getParameter("index");
            Date date;
            int status, index;
            List<ShowDTO> showList;
            
            try {
                status = Integer.parseInt(_status);
                index = Integer.parseInt(_index);
                date = ((_date == null || _date.isEmpty()) ? null : Date.valueOf(_date));
                
                showList = dao.showListFortManagement(name, status, date, index, 7);
                int total = dao.showListFortManagement(name, status, date, index, -1).size();
                int totalPage = total/7;
                if(total % 7 != 0)  totalPage++;
                
                request.setAttribute("name", name);
                request.setAttribute("status", status);
                request.setAttribute("date", date);
                request.setAttribute("index", index);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("showList", showList);
                
                request.getRequestDispatcher("show-management.jsp").forward(request, response);
            } catch (NumberFormatException e) {
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
