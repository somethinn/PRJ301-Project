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
import movie.MovieDAO;
import show.ShowDAO;
import show.ShowDTO;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;
import user.UserDTO;

/**
 *
 * @author ASUS
 */
public class BookingController extends HttpServlet {

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
            out.println("<title>Servlet BookingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingController at " + request.getContextPath() + "</h1>");
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
        UserDTO user = (UserDTO) session.getAttribute("account");
        if (user == null || session == null) {
            response.sendRedirect("login.jsp");
        } else if(user.getUserDOB() == null || user.getUserDOB().equals("")) {
            request.getRequestDispatcher("account-user").forward(request, response);
        } else {
            List<Date> listDate = new ArrayList<>();
            // Lấy ngày hiện tại

            Date currentDate = new Date();

            // Tạo một đối tượng Calendar và đặt ngày hiện tại
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            // Lấy 15 ngày tiếp theo cùng với ngày hôm nay
            for (int i = 0; i <= 15; i++) {
                // Lấy ngày hiện tại và định dạng ngày thành chuỗi
                Date date = calendar.getTime();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            String formattedDate = dateFormat.format(date);
//           

                listDate.add(date);

                // Thêm 1 ngày vào calendar
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            int movieID = Integer.valueOf(request.getParameter("movieID"));
            request.setAttribute("movieID", movieID);
            request.setAttribute("listDate", listDate);
            request.getRequestDispatcher("booking.jsp").forward(request, response);
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
        String date = request.getParameter("date");
//      List<Date> listDate = (List<Date>)request.getAttribute("listDate");

        int movieID = Integer.valueOf(request.getParameter("movieID"));
        List<ShowDTO> showMovieList = new ShowDAO().getShowByMovieIDPublic(movieID, date);
        request.setAttribute("movie", new MovieDAO().getMovieByID(movieID));
        request.setAttribute("showMovieList", showMovieList);
        request.getRequestDispatcher("booking.jsp").forward(request, response);

//        try {
//            movieID = Integer.valueOf(movieID_raw);
//            List<ShowDTO> showMovieList = new ShowDAO().getShowByMovieID(movieID);
////            showMovieList.get(0).getMovie().getMovieID();
//            request.setAttribute("showMovieList", showMovieList);
//            request.getRequestDispatcher("booking.jsp").forward(request, response);
//
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
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
