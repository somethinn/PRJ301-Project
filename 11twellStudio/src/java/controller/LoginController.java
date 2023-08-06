/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import user.UserDAO;
import user.UserDTO;
import user.UserGoogleDTO;

public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDTO userGoogleDTO = getUserInfo(accessToken);
        UserDAO userDAO = new UserDAO();
        
        UserDTO userDTO = UserDTO.builder().userEmail(userGoogleDTO.getEmail())
                .userName(userGoogleDTO.getName())
                .build();
        HttpSession session = request.getSession();
        if (userDAO.checkEmailExist(userGoogleDTO.getEmail()) == true && userDAO.checkUserSetDate(userGoogleDTO.getEmail()) == true && userDAO.checkUserSetChangePassWord(userGoogleDTO.getEmail()) == true) {
            UserDTO u = userDAO.getUserByMail(userGoogleDTO.getEmail());
            session.setAttribute("account", u);
           
                response.sendRedirect("movie-list");
            
        } else if (userDAO.checkEmailExist(userGoogleDTO.getEmail()) == true && userDAO.checkUserSetDate(userGoogleDTO.getEmail()) == false && userDAO.checkUserSetChangePassWord(userGoogleDTO.getEmail()) == true) {
            UserDTO u = userDAO.getUserByMail(userGoogleDTO.getEmail());
            session.setAttribute("account", u);
            
                session.setAttribute("msgSetDate", "You must set your day of birth!!");
                response.sendRedirect("user-account-new.jsp");

            
        } else if (userDAO.checkEmailExist(userGoogleDTO.getEmail()) == true && userDAO.checkUserSetDate(userGoogleDTO.getEmail()) == false && userDAO.checkUserSetChangePassWord(userGoogleDTO.getEmail()) == false) {
            String key = userDAO.checkRegisterGmail(userDTO, userGoogleDTO.getId(), 1);
           UserDTO u = userDAO.getUserByMail(userGoogleDTO.getEmail());
//            session.setAttribute("account", u);
            
                HttpSession session1 = request.getSession();
                session1.setAttribute("keyGoogle", u.getUserEmail() + "-" + key);
                request.setAttribute("message", "The link to verify account has been sent to your email.");
                request.getRequestDispatcher("redirect-mail-message.jsp").forward(request, response);

            
            
        } else {
            String key = userDAO.register(userDTO, userGoogleDTO.getId(), 1);
            UserDTO u = userDAO.getUserGoogleRaw(userDTO.getUserEmail(), userGoogleDTO.getId());
//            session.setAttribute("account", u);
          
                HttpSession session1 = request.getSession();
                session1.setAttribute("keyGoogle", u.getUserEmail() + "-" + key);
                request.setAttribute("message", "The link to verify account has been sent to your email.");
                request.getRequestDispatcher("redirect-mail-message.jsp").forward(request, response);

            
        }

    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        String captchaInput = request.getParameter("captchaInput");

        UserDAO d = new UserDAO();

        HttpSession session = request.getSession();

        UserDTO u = d.check(username, password);
        String captcha = (String) session.getAttribute("captcha");

        // Kiểm tra giá trị CAPTCHA
        if (captcha == null || !captcha.equals(captchaInput)) {
            request.setAttribute("error", "Invalid CAPTCHA!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; // Kết thúc phương thức nếu giá trị CAPTCHA không hợp lệ
        }

        if (u == null) {
            request.setAttribute("error", "Username or Password invalid!!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (u != null && d.checkUserSetDate(username) == false) {
            session.setAttribute("account", u);
            
                request.setAttribute("msgSetDate", "You must set your day of birth first!!");
                response.sendRedirect("account-user");
            
        } else {
            session.setAttribute("account", u);
            
                response.sendRedirect("movie-list");
            
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
