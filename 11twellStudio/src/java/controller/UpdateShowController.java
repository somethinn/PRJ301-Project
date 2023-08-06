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
import movie.MovieDAO;
import room.RoomDAO;
import room.RoomDTO;
import show.ShowDAO;
import show.ShowDTO;
import slot.SlotDAO;
import slot.SlotDTO;

/**
 *
 * @author ASUS
 */
public class UpdateShowController extends HttpServlet {

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
            out.println("<title>Servlet UpdateShowController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateShowController at " + request.getContextPath() + "</h1>");
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
          String id_raw = request.getParameter("id");
        int id;
        ShowDAO showDAO = new ShowDAO();
        SlotDAO slotDAO = new SlotDAO();
        RoomDAO roomDAO = new RoomDAO();
        List<RoomDTO> listRoom = roomDAO.getListRoom();
        List<SlotDTO> listSlot = slotDAO.getListSlot();
        
        try {
            id = Integer.parseInt(id_raw);
            ShowDTO show = showDAO.getShow(id);
           
            request.setAttribute("show", show);
            request.setAttribute("listRoom", listRoom);
            request.setAttribute("listSlot", listSlot);

            request.getRequestDispatcher("edit-show-management.jsp").forward(request, response);
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
           ShowDAO showDAO = new ShowDAO();
        String showID_raw = request.getParameter("show-id");
        String movieID_raw = request.getParameter("show-movie");
        String showDate_raw = request.getParameter("show-date");
        String slotID_raw = request.getParameter("show-slot");
        String roomID_raw = request.getParameter("show-room");
        String price_raw = request.getParameter("show-price");
        String showStatus_raw = request.getParameter("show-status");
        int movieID;
        int showID;
        int slotID;
        int roomID;
        Date showDate;
        double showPrice;
        int showStatus;

        try {

            showID = Integer.parseInt(showID_raw);
            movieID = Integer.parseInt(movieID_raw);
            slotID = Integer.parseInt(slotID_raw);
            roomID = Integer.parseInt(roomID_raw);
            showDate = Date.valueOf(showDate_raw);
            showStatus = Integer.parseInt(showStatus_raw);
            showPrice = Double.parseDouble(price_raw);

            if (showDAO.checkShowExistEdit(showDate, slotID, roomID, showID) == true) {
                response.sendRedirect("show-management");
            } else {
                ShowDTO show = showDAO.getShow(showID);
                show.setRoom(new RoomDAO().getRoomByID(roomID));
                show.setShowPrice(showPrice);
                show.setShowStatus(showStatus);
                show.setShowDate(showDate);
                show.setSlot(new SlotDAO().getSlotByID(slotID));

                showDAO.updateShow(show);
                response.sendRedirect("show-management");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
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
