/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import order.OrderDAO;
import order.OrderDTO;

/**
 *
 * @author Nguyen Thanh
 */
public class OrderManagementController extends HttpServlet {

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
        OrderDAO dao = new OrderDAO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
        
        String _date = request.getParameter("date");
        String mail = request.getParameter("mail");
        String _status = (request.getParameter("status") == null ) ? "3": request.getParameter("status");
        String _index = (request.getParameter("index") == null )? "1" : request.getParameter("index");
        try {
            int status = Integer.parseInt(_status);  
            int index = Integer.parseInt(_index);
            
            Date date_ = null ; // just a date object to change string format
            String date = null;
            if(_date !=null && !_date.isEmpty()){  
                date_ = Date.valueOf(_date);    // format to search in database, why is it a String .-., it wont be this difficult if it was Date datatype
                date = dateFormat.format(date_);
            }
            
            List<OrderDTO> listOrders = dao.getAllOrder(date, mail, status, index, 7);
            
            int total = dao.getAllOrder(date, mail, status, index, -1).size();
            int totalPage = total/7;
            if(total % 7 != 0) totalPage++;
            
            if(_date !=null && !_date.isEmpty()){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // this is to avoid space in url when paging
                date = dateFormat.format(date_);
            }
            request.setAttribute("date", date);
            request.setAttribute("mail", mail);
            request.setAttribute("status", status);
            request.setAttribute("listOrders", listOrders);
            request.setAttribute("index", index);
            request.setAttribute("totalPage", totalPage);
            
            request.getRequestDispatcher("orders-management.jsp").forward(request, response);
            
            
            // need to cast string to date for searching lol :D
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
