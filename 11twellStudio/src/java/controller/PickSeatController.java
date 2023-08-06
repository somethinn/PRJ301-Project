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
import java.util.Timer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import movie.MovieDAO;
import order.OrderDAO;
import seat.SeatDAO;
import seat.SeatDTO;
import show.ShowDAO;
import order.OrderDTO;
import promo.PromoDAO;
import promo.PromoDTO;
import user.UserDTO;
import utils.OrderCleanupTask;

/**
 *
 * @author ASUS
 */
public class PickSeatController extends HttpServlet {

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
            out.println("<title>Servlet PickSeatController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PickSeatController at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("movieID") != null && request.getParameter("showID") != null) {
            int movieID = Integer.parseInt(request.getParameter("movieID"));
            int showID = Integer.parseInt(request.getParameter("showID"));
            SeatDAO seatDAO = new SeatDAO();
            List<SeatDTO> seatList = seatDAO.list(0);

            OrderDAO orderDAO = new OrderDAO();

            for (SeatDTO seatDTO : seatList) {
                if (orderDAO.checkSeatExist(showID, seatDTO.getSeatID()) == true) {
                    seatDTO.setSeatName("X");
                }

            }
            MovieDAO movieDAO = new MovieDAO();
            ShowDAO showDAO = new ShowDAO();
            request.setAttribute("movie", movieDAO.getMovieByID(movieID));
            request.setAttribute("show", showDAO.getShow(showID));
            request.setAttribute("seatList", seatList);

            request.getRequestDispatcher("pickseat.jsp").forward(request, response);
        } else if (request.getAttribute("showID") != null || !request.getAttribute("showID").equals("")) {
            int showID = (int) request.getAttribute("showID");
            SeatDAO seatDAO = new SeatDAO();
            List<SeatDTO> seatList = seatDAO.list(0);

            OrderDAO orderDAO = new OrderDAO();

            for (SeatDTO seatDTO : seatList) {
                if (orderDAO.checkSeatExist(showID, seatDTO.getSeatID()) == true) {
                    seatDTO.setSeatName("X");
                }

            }
            ShowDAO showDAO = new ShowDAO();
            // request.setAttribute("movie", movieDAO.getMovieByID(movieID));
            request.setAttribute("show", showDAO.getShow(showID));
            request.setAttribute("seatList", seatList);
            request.getRequestDispatcher("pickseat.jsp").forward(request, response);

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
//        HttpSession session = request.getSession();
////        List<Integer> seatPendingSession = (List<Integer>)session.getAttribute("seatPending");
////        Integer showIDSession = (Integer)session.getAttribute("showID");
//     List<OrderDTO> listOrderSession = new ArrayList<>();
//            listOrderSession= (List<OrderDTO> ) session.getAttribute("listOrderSession");
//        String[] seatArr;
//        seatArr = request.getParameterValues("seatArr");
//        int showID = Integer.parseInt(request.getParameter("showID"));
//
//        List<Integer> seatPending = new ArrayList<>();
//        for (int i = 0; i < seatArr.length; i++) {
//            if (seatArr[i] != null) {
//                seatPending.add(Integer.parseInt(seatArr[i]));
//            }
//        }
//     listOrderSession.add(new OrderDTO(3, new UserDAO().getUser(1), new ShowDAO().getShow(showID), seatPending));
//
////         OrderDTO orderPending = new OrderDTO();
////         orderPending.setSeatIDList(seatPending);
////         orderPending.setShow(new ShowDAO().getShow(showID));
////         orderPending.setUser(new UserDAO().getUser(1));
////         orderPending.setOrderID(2);
//        OrderDTO orderPending;
//        orderPending = new OrderDTO(2, new UserDAO().getUser(1), new ShowDAO().getShow(showID), seatPending);
//
//        boolean checkSeatExist = false;
////        seatOrderList.removeAll(seatOrderList);
//        if (listOrderSession != null) {
//
//            for (int i = 0; i < listOrderSession.size(); i++) {
//                if (listOrderSession.get(i).getShow().getShowID() == showID) {
//                    for (int j = 0; j < listOrderSession.get(i).getSeatIDList().size(); j++) {
//                        if ((Objects.equals(listOrderSession.get(i).getSeatIDList().get(j), seatPending.get(j)))) {
//                            checkSeatExist = true;
//                            break;
//                        }
//                    }
//
//                }
//            }
//
//            if (checkSeatExist == true) {
//                response.sendRedirect("movieList");
//            } else {
//                listOrderSession.add(orderPending);
//                session.setAttribute("listOrderSession", listOrderSession);
//                session.setAttribute("orderPending", orderPending);
//                request.getRequestDispatcher("order.jsp").forward(request, response);
//            }
//        } else {
//            listOrderSession.add(orderPending);
//            session.setAttribute("listOrderSession", listOrderSession);
//            session.setAttribute("orderPending", orderPending);
//            request.getRequestDispatcher("order.jsp").forward(request, response);
//        }
//     HttpSession session = request.getSession();
//        List<Integer> seatPendingSession = (List<Integer>)session.getAttribute("seatPending");
//        Integer showIDSession = (Integer)session.getAttribute("showID");
//      
//      
//        
//        String[] seatArr;
//        seatArr = request.getParameterValues("seatArr");
//        int showID = Integer.parseInt(request.getParameter("showID"));
//        
//        List<Integer> seatPending = new ArrayList<>();
//         for (int i = 0; i < seatArr.length; i++) {
//            if (seatArr[i] != null) {             
//                  seatPending.add(Integer.parseInt(seatArr[i]));
//            }
//        }
//        boolean checkSeatExist = false;
////        seatOrderList.removeAll(seatOrderList);
//        if(seatPendingSession != null && showIDSession != null) {
//            for (int i = 0; i < seatPendingSession.size(); i++) {
//                for (int j = 0; j < seatPending.size() ; j++) {
//                    if((Objects.equals(seatPendingSession.get(i), seatPending.get(j))) && showID == showIDSession ) {
//                        checkSeatExist = true;
//                        break;
//                    }
//                }
//            }
//            
//           if (checkSeatExist == true) {        
//            response.sendRedirect("movieList");
//           } else {
//            session.setAttribute("seatPending", seatPending);
//            session.setAttribute("showID", showID);
//            request.getRequestDispatcher("order.jsp").forward(request, response);
//           }
//        } else {
//            session.setAttribute("seatPending", seatPending);
//            session.setAttribute("showID", showID);
//            request.getRequestDispatcher("order.jsp").forward(request, response);
//        }

        String[] seatArr;
        seatArr = request.getParameterValues("seatArr");

        List<SeatDTO> seatOrderList = new ArrayList<>();
        int showID = Integer.parseInt(request.getParameter("showID"));
        List<String> pickAgainMsg = new ArrayList<>();
        SeatDAO seatDAO = new SeatDAO();
        OrderDAO orderDAO = new OrderDAO();

        if (seatArr == null) {
            List<SeatDTO> seatList = seatDAO.list(0);

            for (SeatDTO seatDTO : seatList) {
                if (orderDAO.checkSeatExist(showID, seatDTO.getSeatID()) == true) {
                    seatDTO.setSeatName("X");
                }

            }
            MovieDAO movieDAO = new MovieDAO();
            ShowDAO showDAO = new ShowDAO();
            request.setAttribute("movie", movieDAO.getMovieByID(showDAO.getShow(showID).getMovie().getMovieID()));
            request.setAttribute("show", showDAO.getShow(showID));
            request.setAttribute("seatList", seatList);
            for (int j = 0; j < 10; j++) {

            }
            request.setAttribute("pickingSeatRequired", "Please pick your seat!!!!");

            request.getRequestDispatcher("pickseat.jsp").forward(request, response);
        } else {
             for (int i = 0; i < seatArr.length; i++) {
            if (seatArr[i] != null) {
                seatOrderList.add(seatDAO.getSeatBySeatID(Integer.parseInt(seatArr[i])));
            }
        }

        for (int i = 0; i < seatOrderList.size(); i++) {
            if (orderDAO.checkSeatExist(showID, seatOrderList.get(i).getSeatID())) {
                pickAgainMsg.add(seatDAO.getSeatNameByID(seatOrderList.get(i).getSeatID()));
            }

        }
        //orderDAO.checkSeatExist(0, 0)
        for (int i = 0; i < seatOrderList.size(); i++) {
            if (orderDAO.checkSeatExist(showID, seatOrderList.get(i).getSeatID())) {

                List<SeatDTO> seatList = seatDAO.list(0);

                for (SeatDTO seatDTO : seatList) {
                    if (orderDAO.checkSeatExist(showID, seatDTO.getSeatID()) == true) {
                        seatDTO.setSeatName("X");
                    }

                }
                MovieDAO movieDAO = new MovieDAO();
                ShowDAO showDAO = new ShowDAO();
                request.setAttribute("movie", movieDAO.getMovieByID(showDAO.getShow(showID).getMovie().getMovieID()));
                request.setAttribute("show", showDAO.getShow(showID));
                request.setAttribute("seatList", seatList);
                for (int j = 0; j < 10; j++) {

                }
                request.setAttribute("pickingAgainMsg", pickAgainMsg);

                request.getRequestDispatcher("pickseat.jsp").forward(request, response);

            }

        }

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("account");

        OrderDTO orderRaw = OrderDTO.builder().show(new ShowDAO()
                .getShow(showID))
                .seatIDList(seatOrderList)
                .user(user)
                .build();

        orderDAO.insertOrder(showID, user.getUserID());

        for (int i = 0; i < seatOrderList.size(); i++) {
            orderDAO.insertOrderDetails(orderDAO.getOrderIDByShowIDAndUserID(user.getUserID(), showID).get(orderDAO.getOrderIDByShowIDAndUserID(user.getUserID(), showID).size() - 1), seatOrderList.get(i).getSeatID(), new ShowDAO().getShow(showID).getShowPrice());

        }
        orderDAO.updateTotalOrderPrice(orderDAO.getOrderIDByShowIDAndUserID(user.getUserID(), showID).get(orderDAO.getOrderIDByShowIDAndUserID(user.getUserID(), showID).size() - 1));

        orderRaw = orderDAO.getOrderByOrderID(orderDAO.getOrderIDByShowIDAndUserID(user.getUserID(), showID).get(orderDAO.getOrderIDByShowIDAndUserID(user.getUserID(), showID).size() - 1));

        PromoDAO promoDAO = new PromoDAO();
        List<PromoDTO> pList = promoDAO.getAllPromo();
        request.setAttribute("pList", pList);
        
        Timer timer = new Timer();

        // Lên lịch thực thi một TimerTask sau 10 phút
        timer.schedule(new OrderCleanupTask(orderRaw.getOrderID()), 60 * 1000); // 10 phút (10 * 60 * 1000 milliseconds)  - // 60 * 1000 - 1phut

        request.setAttribute("order", orderRaw);

        request.getRequestDispatcher("order.jsp").forward(request, response);

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
