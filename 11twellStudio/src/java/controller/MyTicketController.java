/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import order.OrderDAO;
import order.OrderDTO;
import seat.SeatDAO;
import user.UserDTO;

/**
 *
 * @author ASUS
 */
public class MyTicketController extends HttpServlet {

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
            out.println("<title>Servlet MyTicketController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyTicketController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        if (session.getAttribute("account") == null || session == null) {
            response.sendRedirect("login.jsp");
        } else {
            UserDTO user = (UserDTO) session.getAttribute("account");
            OrderDAO orderDAO = new OrderDAO();
            SeatDAO seatDAO = new SeatDAO();

            List<String> listSeatName = new ArrayList<>();
//        List<OrderDTO> listOrder = orderDAO.getAllOrderOfUser(user.getUserID());
//        for (int i = 0; i < listOrder.size(); i++) {
//            for (int j = 0; j < listOrder.get(i).getSeatIDList().size(); j++) {
//                listSeatName.add(seatDAO.getSeatNameByID(listOrder.get(i).getSeatIDList().get(j)));
//            }
//
//        }

//        request.setAttribute("listSeatName", listSeatName);
            String sortBy = request.getParameter("sortBy");
            
            if(sortBy ==  null || sortBy.equals("")) {
                sortBy = "DESC";
            }
            List<OrderDTO> listOrder = orderDAO.searchOrderList(sortBy, user.getUserID());

            request.setAttribute("listOrder", listOrder);
            request.getRequestDispatcher("my-ticket.jsp").forward(request, response);
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
