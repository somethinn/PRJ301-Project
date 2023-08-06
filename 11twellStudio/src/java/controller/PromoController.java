/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import order.OrderDAO;
import order.OrderDTO;
import promo.PromoDAO;
import promo.PromoDTO;

/**
 *
 * @author ASUS
 */
public class PromoController extends HttpServlet {

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
            out.println("<title>Servlet PromoController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PromoController at " + request.getContextPath() + "</h1>");
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

        String promoCode = request.getParameter("promoCode");
        String orderID = request.getParameter("orderID");

        PromoDAO promoDAO = new PromoDAO();
        PromoDTO promoDTO = promoDAO.getPromoByCode(promoCode);
                // Lấy ngày hiện tại
              Calendar calendar = Calendar.getInstance();
             Date currentDate = calendar.getTime();

            // Tạo một đối tượng Calendar và đặt ngày hiện tại
       
        OrderDAO orderDAO = new OrderDAO();
        if (promoDTO == null || promoCode.equals("") || promoCode == null || currentDate.compareTo(promoDTO.getPromoStartDate()) == -1 || currentDate.compareTo(promoDTO.getPromoEndDate()) == 1) {
            OrderDTO order = orderDAO.getOrderByOrderID(Integer.parseInt(orderID));
            request.setAttribute("promoMsg", "Code invalid");
            request.setAttribute("order", order);
            request.getRequestDispatcher("order.jsp").forward(request, response);
        } else {
            orderDAO.insertPromoID(promoDTO.getPromoID());
            OrderDTO orderDTO = orderDAO.getOrderByOrderID(Integer.parseInt(orderID));
            orderDTO.setOrderTotalPrice(orderDTO.getOrderTotalPrice() - orderDTO.getOrderTotalPrice() * promoDTO.getPromoDiscount());
            orderDAO.updatePriceAfterAddPromo(orderDTO);
            request.setAttribute("promoMsg", "Added successfully, total price discount " + promoDTO.getPromoContent());
            request.setAttribute("order", orderDTO);

            request.getRequestDispatcher("order.jsp").forward(request, response);
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
