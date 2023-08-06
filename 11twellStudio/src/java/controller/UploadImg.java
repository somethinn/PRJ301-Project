/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import movie.MovieDAO;

/**
 *
 * @author Admin
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, //1mb
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20)
@WebServlet(name = "UploadImg", urlPatterns = {"/UploadImg"})
public class UploadImg extends HttpServlet {

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
            out.println("<title>Servlet UploadImg</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadImg at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("uploadFile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     *     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String file = request.getParameter("file");
        String mid = request.getParameter("mid");
//        response.getWriter().println(file);
        response.getWriter().println(mid);
        Part part = request.getPart("file");
        String fileName = extractFileName(part);
        fileName = new File(fileName).getName();
        part.write(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
        MovieDAO dao = new MovieDAO();
        dao.insertMovieImage(fileName, mid);

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

    //convert data to string 
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
   
  
    
    
//     public List<String> getAbsoluteImagePath() {
//        java.io.File file = new java.io.File("");
//        String imagePath_raw = file.getAbsolutePath() + "\\web\\images";
//        String[] pattern;
//        pattern = imagePath_raw.split("[\\\\]");
//        List<String> imagePath = new ArrayList<>();
//       
//        for (int i = 0; i < pattern.length; i++) {
//            imagePath.add(pattern[i]);
//            
//        }       
//        return imagePath;
//        //D:\2023_Semester4_SUMMER\PRJ301\thayDung\Assignment\11twellStudio\web\images
//    }  
    
   
    
  

    public String getAbsoluteImagePath() {
       java.io.File file = new java.io.File("web\\images");
        String imagePath= file.getAbsolutePath();
               
    
        return imagePath;
        //D:\2023_Semester4_SUMMER\PRJ301\thayDung\Assignment\11twellStudio\web\images
    }
//
    public File getFolderUpload() {
        File folderUpload = new File("D:\\2023_Semester4_SUMMER\\PRJ301\\thayDung\\Assignment\\11twellStudio\\web\\images");
//        String folder = "D:\\2023_Semester4_SUMMER\\PRJ301\\thayDung\\Assignment\\11twellStudio\\web\\images";
        String folder = getAbsoluteImagePath();
//        File folderUpload = new File(folder);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
    
//    public static void main(String[] args) {
//        UploadImg u = new UploadImg();
//       
////        System.out.println("D:\\2023_Semester4_SUMMER\\PRJ301\\thayDung\\Assignment\\11twellStudio\\web\\images");
////        System.out.println(u.getAbsoluteImagePath1());
////        for (String path : u.getAbsoluteImagePath()) {
////            System.out.println(path);
////        }
////          List<String> list = u.getAbsoluteImagePath();
////        String folder = list.get(0) + "\\\\" +list.get(1) + "\\\\" +list.get(2) + "\\\\"  + list.get(3) + "\\\\" + list.get(4) + "\\\\" + list.get(5) + "\\\\"  + list.get(6) + "\\\\" + list.get(7);
//
// java.io.File file = new java.io.File("web\\images");
// String imagePath= file.getAbsolutePath();
//                System.out.println(imagePath);
//                       String folder = "D:\\2023_Semester4_SUMMER\\PRJ301\\thayDung\\Assignment\\11twellStudio\\web\\images";
//                 System.out.println(folder);
//
//    }

}
