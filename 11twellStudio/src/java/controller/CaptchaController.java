/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class CaptchaController extends HttpServlet {

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
            out.println("<title>Servlet CaptchaController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CaptchaController at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");

        if (action != null && action.equals("generateCaptcha")) {
            generateNewCaptcha(request, response);
        } else {
            String captcha = generateCaptcha();

            // Lưu giá trị CAPTCHA vào session
            HttpSession session = request.getSession();
            session.setAttribute("captcha", captcha);

            // Tạo hình ảnh CAPTCHA
            BufferedImage image = generateCaptchaImage(captcha);

            // Gửi hình ảnh CAPTCHA về client
            response.setContentType("image/png");
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "png", out);
            out.close();
        }
    }

    private String generateCaptcha() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6; // Độ dài của giá trị CAPTCHA
        StringBuilder captcha = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            captcha.append(allowedChars.charAt(index));
        }

        return captcha.toString();
    }

    private BufferedImage generateCaptchaImage(String captcha) {
        int width = 180; // Độ rộng của hình ảnh CAPTCHA
        int height = 70; // Chiều cao của hình ảnh CAPTCHA

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        // Đặt màu nền cho hình ảnh CAPTCHA
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, width, height);

        // Đặt màu văn bản cho hình ảnh CAPTCHA
        graphics.setColor(Color.DARK_GRAY);

        // Đặt phông chữ cho văn bản
        Font font = new Font("Arial", Font.BOLD, 30);
        graphics.setFont(font);

        // Áp dụng biến đổi affine để nghiêng chữ
        graphics.shear(0.2, 0.0);

        // Vẽ văn bản lên hình ảnh CAPTCHA
        graphics.drawString(captcha, 20, 35);

        // Áp dụng độ nhiễu bằng cách vẽ các dấu gạch lộn xộn
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = x1 + random.nextInt(10);
            int y2 = y1 + random.nextInt(10);
            graphics.drawLine(x1, y1, x2, y2);
        }

        // Thêm các hiệu ứng bổ sung cho hình ảnh CAPTCHA (tùy chọn)
        // ...
        graphics.dispose();

        return image;
    }

    private void generateNewCaptcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String captcha = generateCaptcha();

        // Lưu giá trị CAPTCHA mới vào session
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captcha);

        // Tạo hình ảnh CAPTCHA mới
        BufferedImage image = generateCaptchaImage(captcha);

        // Gửi hình ảnh CAPTCHA mới về client
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
        out.close();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////

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
