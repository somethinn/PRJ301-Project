/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import promo.PromoDAO;
import promo.PromoDTO;

/**
 *
 * @author ASUS
 */
public class PromoManagementController extends HttpServlet {

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
        PromoDAO dao = new PromoDAO();
        
        String code = request.getParameter("code");
        String _status = request.getParameter("status")==null?"3": request.getParameter("status");
        String _startDate = request.getParameter("startDate");
        String _endDate = request.getParameter("endDate");
        String _index = request.getParameter("index")==null? "1": request.getParameter("index");
        
        try {
            int status = Integer.parseInt(_status);
            Date startDate = (_startDate==null ||_startDate.isEmpty())? null :Date.valueOf(_startDate);
            Date endDate = (_endDate==null || _endDate.isEmpty())? null :Date.valueOf(_endDate);
            int index = Integer.parseInt(_index);
                       
            List<PromoDTO> list = dao.getPromoListForManagement(code, status, startDate, endDate, index, 7);
            
            int total = dao.getPromoListForManagement(code, status, startDate, endDate, index, -1).size();
            int totalPage = total/7;
            if(total % 7 != 0) totalPage++;
            
            request.setAttribute("code", code);
            request.setAttribute("status", status);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("index", index);
            request.setAttribute("pList", list);
            request.setAttribute("totalPage", totalPage);
            
            request.getRequestDispatcher("promo-management.jsp").forward(request, response);
            
        } catch (IOException | NumberFormatException | ServletException e) {
            System.err.println(e);
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
