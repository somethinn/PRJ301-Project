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
public class ChangPasswordForgotController extends HttpServlet {

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
            out.println("<title>Servlet ChangPasswordForgotController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangPasswordForgotController at " + request.getContextPath() + "</h1>");
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
        String mail = request.getParameter("email");
        String key = request.getParameter("key");

        HttpSession session = request.getSession();

        String keyForgot = (String) session.getAttribute("keyForgot");
        String[] splitKey = keyForgot.split("-");
        if (mail.equals(splitKey[0]) && key.equals(splitKey[1])) {
            session.removeAttribute("keyForgot");
            session.setAttribute("userMail", mail);
            request.getRequestDispatcher("change-password-forgot.jsp").forward(request, response);

        } else {
            response.getWriter().print("Change failed!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        request.getRequestDispatcher("change-password-forgot.jsp").forward(request, response);

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
        HttpSession session = request.getSession();
        String userMail = (String) session.getAttribute("userMail");
        String newPassword = request.getParameter("userNewPassword");
        String confirmNewPassword = request.getParameter("confirmUserNewPassword");

        if (!newPassword.equals(confirmNewPassword)) {

            request.setAttribute("wrongPassConfirmMsg", "Confirm password is wrong, please check!!");
            request.getRequestDispatcher("change-password-forgot.jsp").forward(request, response);

        } else {

            new UserDAO().updatePassword(userMail, newPassword);
            UserDTO u = new UserDAO().getUserByMail(userMail);



            if (u.getUserRole() == 0) { //admin
                request.getRequestDispatcher("login-admin.jsp").forward(request, response);
            } else {
                request.setAttribute("forgotMsg", "Reset password successfully!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);

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
