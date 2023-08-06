/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import post.PostDAO;
import post.PostDTO;

/**
 *
 * @author Nguyen Thanh
 */
public class PostManagementController extends HttpServlet {

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
        String _status = request.getParameter("status") == null ? "3" : request.getParameter("status");
        String _index = request.getParameter("index") == null ? "1" : request.getParameter("index");
        String name = request.getParameter("name");
        
        PostDAO dao = new PostDAO();
        
        try {
            int status =Integer.parseInt(_status);
            int index = Integer.parseInt(_index);
            
            List<PostDTO> pList = dao.searchPostForManagement(name, status, index, 7);
            int total = dao.searchPostForManagement(name, status, index, -1).size();
            int totalPage = total/7;
            if(total % 7 != 0) totalPage++;
            
            request.setAttribute("pList", pList);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("index", index);
            request.setAttribute("status", status);
            request.setAttribute("name", name);
            
            request.getRequestDispatcher("post-management.jsp").forward(request, response);
        } catch (Exception e) {
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
