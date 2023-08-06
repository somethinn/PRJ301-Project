/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author ASUS
 */
public class UserAccountController extends HttpServlet {

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
            out.println("<title>Servlet UserAccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserAccountController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

        UserDTO user = (UserDTO) session.getAttribute("account");
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            session.setAttribute("account", user);
            request.getRequestDispatcher("user-account-new.jsp").forward(request, response);

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
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone").trim();
        String userEmail = request.getParameter("email");
//        String userPassword = request.getParameter("password").trim();
//        String comfirmPassword = request.getParameter("confirmPassword").trim();
        String dOB_raw = request.getParameter("userDOB");
        String userRegion = request.getParameter("userRegion");
        String userGender = request.getParameter("userGender");
        Date DOB;

        HttpSession session = request.getSession();
        UserDTO user_old = (UserDTO) session.getAttribute("account");

        UserDAO userDAO = new UserDAO();

        if (dOB_raw == null || dOB_raw.equals("")) {

            UserDTO userDTO = UserDTO.builder().userName(userName)
                    .userEmail(userEmail)
                    .userGender(userGender)
                    .userPhone(userPhone)
                    .userRegion(userRegion)
                    .build();
            userDAO.updateUserProfile(userDTO, user_old.getUserID());
            userDTO = userDAO.getUser(user_old.getUserID());
            session.setAttribute("account", userDTO);

            request.setAttribute("msgSuccess", "Change failed!!");
            request.getRequestDispatcher("user-account-new.jsp").forward(request, response);

        } else {
            DOB = Date.valueOf(dOB_raw);
            UserDTO userDTO = UserDTO.builder().userName(userName)
                    .userEmail(userEmail)
                    .userGender(userGender)
                    .userPhone(userPhone)
                    .userDOB(DOB)
                    .userRegion(userRegion)
                    .build();

            if (userDAO.isActive(userEmail)) {
                userDAO.updateUserProfile(userDTO, user_old.getUserID());
                userDTO = userDAO.getUser(user_old.getUserID());

                if (userDAO.updateUserProfile(userDTO, user_old.getUserID()) == true) {
                    request.setAttribute("msgSuccess", "Change successfully!!");
                    session.setAttribute("account", userDTO);
                    request.getRequestDispatcher("user-account-new.jsp").forward(request, response);
                } else {
                    request.setAttribute("msgSuccess", "Change failed!!");
                    request.getRequestDispatcher("user-account-new.jsp").forward(request, response);

                }

            } else {
                userDAO.updateUserProfileAndDate(userDTO, user_old.getUserID());
                userDAO.activeAccount(userEmail);
                userDTO = userDAO.getUser(user_old.getUserID());

                if ((userDAO.updateUserProfileAndDate(userDTO, user_old.getUserID())) == true && (userDAO.checkUserSetDate(userEmail) == true)) {
                    request.setAttribute("msgSuccess", "Change successfully!!");
                    session.setAttribute("account", userDTO);
                    request.getRequestDispatcher("user-account-new.jsp").forward(request, response);
                } else {
                    request.setAttribute("msgSuccess", "Change failed!!");
                    request.getRequestDispatcher("user-account-new.jsp").forward(request, response);

                }
            }

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
