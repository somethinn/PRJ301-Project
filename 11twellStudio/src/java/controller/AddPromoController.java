/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import genre.GenreDAO;
import genre.GenreDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import movie.MovieDAO;
import movie.MovieDTO;
import promo.PromoDAO;
import promo.PromoDTO;
//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Nguyen Thanh
 */
@WebServlet(name = "AddMovieController", urlPatterns = {"/add-movie"})
public class AddPromoController extends HttpServlet {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

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
            out.println("<title>Servlet AddMovieController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddMovieController at " + request.getContextPath() + "</h1>");
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

        request.getRequestDispatcher("add-promo-management.jsp").forward(request, response);
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
        PromoDAO promoDAO = new PromoDAO();

        String promoDiscount_raw = request.getParameter("promoDiscount");
        String promoStartDate_raw = request.getParameter("promoStartDate");
        String promoEndDate_raw = request.getParameter("promoEndDate");
        String promoStatus_raw = request.getParameter("promoStatus");
        Date promoStartDate;
        Date promoEndDate;
        Float promoDiscount;
        Integer promoStatus;
        String promoContent;

        String promoCode = generateRandomString(10);
        while (promoDAO.getPromoByCode(promoCode) != null) {
                promoCode = generateRandomString(10);

        }

        promoDiscount = Float.parseFloat(promoDiscount_raw);
        promoStartDate = Date.valueOf(promoStartDate_raw);
        promoEndDate = Date.valueOf(promoEndDate_raw);
        promoStatus = Integer.parseInt(promoStatus_raw);

        if (promoDiscount_raw.equals("0.02")) {
            promoContent = "2%";
            PromoDTO promoDTO = PromoDTO.builder()
                    .promoCode(promoCode)
                    .promoContent(promoContent)
                    .promoDiscount(promoDiscount)
                    .promoStartDate(promoStartDate)
                    .promoEndDate(promoEndDate)
                    .promoStatus(promoStatus)
                    .build();
            promoDAO.insertPromo(promoDTO);

        } else if (promoDiscount_raw.equals("0.025")) {
            promoContent = "5%";
            PromoDTO promoDTO = PromoDTO.builder()
                    .promoCode(promoCode)
                    .promoContent(promoContent)
                    .promoDiscount(promoDiscount)
                    .promoStartDate(promoStartDate)
                    .promoEndDate(promoEndDate)
                    .promoStatus(promoStatus)
                    .build();
            promoDAO.insertPromo(promoDTO);

        } else if (promoDiscount_raw.equals("0.1")) {
            promoContent = "10%";
            PromoDTO promoDTO = PromoDTO.builder()
                    .promoCode(promoCode)
                    .promoContent(promoContent)
                    .promoDiscount(promoDiscount)
                    .promoStartDate(promoStartDate)
                    .promoEndDate(promoEndDate)
                    .promoStatus(promoStatus)
                    .build();
            promoDAO.insertPromo(promoDTO);

        } else if (promoDiscount_raw.equals("0.2")) {
            promoContent = "20%";
            PromoDTO promoDTO = PromoDTO.builder()
                    .promoCode(promoCode)
                    .promoContent(promoContent)
                    .promoDiscount(promoDiscount)
                    .promoStartDate(promoStartDate)
                    .promoEndDate(promoEndDate)
                    .promoStatus(promoStatus)
                    .build();
            promoDAO.insertPromo(promoDTO);

        } else if (promoDiscount_raw.equals("0.3")) {
            promoContent = "30%";
            PromoDTO promoDTO = PromoDTO.builder()
                    .promoCode(promoCode)
                    .promoContent(promoContent)
                    .promoDiscount(promoDiscount)
                    .promoStartDate(promoStartDate)
                    .promoEndDate(promoEndDate)
                    .promoStatus(promoStatus)
                    .build();
            promoDAO.insertPromo(promoDTO);

        } else if (promoDiscount_raw.equals("0.5")) {
            promoContent = "50%";
            PromoDTO promoDTO = PromoDTO.builder()
                    .promoCode(promoCode)
                    .promoContent(promoContent)
                    .promoDiscount(promoDiscount)
                    .promoStartDate(promoStartDate)
                    .promoEndDate(promoEndDate)
                    .promoStatus(promoStatus)
                    .build();
            promoDAO.insertPromo(promoDTO);

        } else if (promoDiscount_raw.equals("0.7")) {
            promoContent = "70%";
            PromoDTO promoDTO = PromoDTO.builder()
                    .promoCode(promoCode)
                    .promoContent(promoContent)
                    .promoDiscount(promoDiscount)
                    .promoStartDate(promoStartDate)
                    .promoEndDate(promoEndDate)
                    .promoStatus(promoStatus)
                    .build();
            promoDAO.insertPromo(promoDTO);

        } else if (promoDiscount_raw.equals("1")) {
            promoContent = "100%";
            PromoDTO promoDTO = PromoDTO.builder()
                    .promoCode(promoCode)
                    .promoContent(promoContent)
                    .promoDiscount(promoDiscount)
                    .promoStartDate(promoStartDate)
                    .promoEndDate(promoEndDate)
                    .promoStatus(promoStatus)
                    .build();
            promoDAO.insertPromo(promoDTO);

        }
        response.sendRedirect("promo-management");
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
