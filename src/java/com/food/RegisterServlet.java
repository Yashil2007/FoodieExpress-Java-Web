package com.food;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile"); 
        String pass = request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/food_order_db", "root", "yashil@7");
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name, email, mobile, password) VALUES(?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, pass);

            int result = ps.executeUpdate();

            if (result > 0) {
                response.sendRedirect("login.html");
            } else {
                out.println("<h3>Registration failed. Please try again.</h3>");
            }
            con.close();
        } catch (Exception e) {
            out.println("<div style='text-align:center; margin-top:50px;'>");
            out.println("<h3 style='color:red'>Error: " + e.getMessage() + "</h3>");
            out.println("<a href='register.html'>Back to Registration</a>");
            out.println("</div>");
        }
    }
}