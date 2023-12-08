/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author amritamaharjan
 */
@WebServlet(urlPatterns = {"/foodInfo"})
public class foodInfo extends HttpServlet {

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
                String name = request.getParameter("fname");
                String Foodname = request.getParameter("ffname");
                String date = request.getParameter("date");
                out.println(date);
                  String time = request.getParameter("time");
                String ContactNo = request.getParameter("con");
                String quality = request.getParameter("food");
                String type = request.getParameter("country");
                String desp = request.getParameter("desc");
               // out.write(Fname + "," + Address + "," + ContactNo + "," + Password + "," + cp);
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");
                    String sql = "INSERT INTO information(Donor_Name,Food_name,Prepare_date,Prepare_time,ContactNo,Food_Quality,Item,Description) VALUES(?,?,?,?,?,?,?,?)";
                    PreparedStatement statement = con.prepareStatement(sql);
//                   statement.setInt(1,3);
                    statement.setString(1, name);
                    statement.setString(2, Foodname);
                    statement.setString(3, date);
                     statement.setString(4, time);
                    statement.setString(5, ContactNo);
                     statement.setString(6, quality);
                     statement.setString(7, type);
                    statement.setString(8, desp);
                    statement.executeUpdate();
                    con.close();
           
                  RequestDispatcher rd = request.getRequestDispatcher("landing.jsp");
                   rd.include(request, response);

                } catch (Exception ex) {
                    out.println(ex.getMessage());
                }
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
        processRequest(request, response);
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
