package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Nguyen Thanh
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String userPhone = request.getParameter("userPhone");
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        String comfirmPassword = request.getParameter("confirmPassword");
        String dOB_raw = request.getParameter("userDOB");
        String userRegion = request.getParameter("userRegion");
        String userGender = request.getParameter("userGender");

        HttpSession session = request.getSession();

        String captchaInput = request.getParameter("captchaInput");
        String captcha = (String) session.getAttribute("captcha");

        if (captcha == null || !captcha.equals(captchaInput)) {
            request.setAttribute("errorCaptcha", "Invalid CAPTCHA!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; // Kết thúc phương thức nếu giá trị CAPTCHA không hợp lệ
        }

        try {
            if (new UserDAO().checkEmailExist(userEmail) == true) {
                request.setAttribute("error", "Account is exist");
                request.getRequestDispatcher("register.jsp").forward(request, response);

            } else if (!comfirmPassword.equals(userPassword)) {
                request.setAttribute("msg", "Confirm password not match");
                request.getRequestDispatcher("register.jsp").forward(request, response);

            } else {
                String key = new UserDAO().register(userName, userEmail, userPassword, userGender, userPhone, userRegion, dOB_raw, 1);
                HttpSession session1 = request.getSession();
                session1.setAttribute("key", userEmail + "-" + key);

                request.setAttribute("message", "The link to verify account has been sent to your email.");
                request.getRequestDispatcher("redirect-mail-message.jsp").forward(request, response);

//                try {
//
//                    //DOB = Date.valueOf(dOB_raw);
//                    if (dOB_raw == null) {
//                        UserDTO userDTO = UserDTO.builder().userName(userName)
//                                .userEmail(userEmail)
//                                .userGender(userGender)
//                                .userPhone(userPhone)
//                                .userRegion(userRegion)
//                                .build();
//                        //UserDTO user = new UserDTO(userPhone, userName, userEmail, userRegion, userPhone, userRegion, dOB, userPhone);
//                        String key = new UserDAO().register(userDTO, userPassword, 1);
//                        userDTO = new UserDAO().check(userEmail, userPassword);
//                        HttpSession session1 = request.getSession();
//                        session1.setAttribute("key", userDTO.getUserEmail() + "-" + key);
//                        response.getWriter().print("register duoc rui nhe");
//
////                        request.setAttribute("message", "The link to verify account has been sent to your email.");
////                      
//////                        request.getRequestDispatcher("redirect-mail-message.jsp").forward(request, response);
////                         response.sendRedirect("redirect-mail-message.jsp");
//                    } else {
//                        DOB = Date.valueOf(dOB_raw);
//                        UserDTO userDTO = UserDTO.builder().userName(userName)
//                                .userEmail(userEmail)
//                                .userGender(userGender)
//                                .userPhone(userPhone)
//                                .userDOB(DOB)
//                                .userRegion(userRegion)
//                                .build();
//                        //UserDTO user = new UserDTO(userPhone, userName, userEmail, userRegion, userPhone, userRegion, dOB, userPhone);
//                         String key = new UserDAO().register(userDTO, userPassword, 1);
//                        userDTO = new UserDAO().check(userEmail, userPassword);
//                        HttpSession session1 = request.getSession();
//                        session1.setAttribute("key", userDTO.getUserEmail() + "-" + key);
//                        response.getWriter().print("register duoc rui nhe");
////                        request.setAttribute("message", "The link to verify account has been sent to your email.");
//////                        request.getRequestDispatcher("redirect-mail-message.jsp").forward(request, response);
////                        response.sendRedirect("redirect-mail-message.jsp");
//                    }
//
////                response.sendRedirect("login.jsp");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
