/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ChangePasswordController extends HttpServlet {

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
            out.println("<title>Servlet ChangePasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordController at " + request.getContextPath() + "</h1>");
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
        String key = request.getParameter("key");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("account");
        String keyMail = (String) session.getAttribute("keyGoogle");
        String[] splitKey = keyMail.split("-");
        if (email.equals(splitKey[0]) && key.equals(splitKey[1])) {
            session.removeAttribute("keyGoogle");
            session.setAttribute("userGoogle", new UserDAO().getUserByMail(email));
            request.setAttribute("message", "You must change your password first");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);

        } else {
            response.getWriter().print("Change failed!");
            request.setAttribute("registerMsg", "Sign-up failed! Please try again ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        request.getRequestDispatcher("change-password.jsp").forward(request, response);
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
        String password = request.getParameter("userPassword");
        String newPassword = request.getParameter("userNewPassword");
        String confirmNewPassword = request.getParameter("confirmUserNewPassword");
        HttpSession session = request.getSession();
        UserDTO userGoogle = (UserDTO) session.getAttribute("userGoogle");

        if ((!password.equals(new UserDAO().getPasswordByMail(userGoogle.getUserEmail())))) {

            request.setAttribute("wrongPassMsg", "Password is wrong, please check the password we have sent to your mailbox before!");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
        } else if (!newPassword.equals(confirmNewPassword)) {

            request.setAttribute("wrongPassConfirmMsg", "Confirm password is wrong, please check!!");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);

        } else {

            UserDTO u = new UserDAO().getUserByMail(userGoogle.getUserEmail());
            new UserDAO().updatePassword(u.getUserEmail(), newPassword);
            new UserDAO().activeUserGoogleIsChane(userGoogle.getUserEmail());
            HttpSession session1 = request.getSession();
            session1.setAttribute("account", u);
            if (u.getUserRole() == 0) { //admin
                request.getRequestDispatcher("login-admin.jsp").forward(request, response);
            } else {
                session1.setAttribute("msgSetDate", "You must set your day of birth!!");
                response.sendRedirect("user-account-new.jsp");

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
