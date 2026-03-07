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
        String password = request.getParameter("password");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/food_order_db", "root", "yashil@7");

            PreparedStatement ps = con.prepareStatement(
                    "insert into users(name,email,password) values(?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int result = ps.executeUpdate();
            
            if(result > 0) {
                out.println("<h3>Registration Successful!</h3>");
                out.println("<a href='login.html'>Click here to Login</a>");
            }
            con.close();

        } catch (Exception e) {
            out.println("<h3>Registration Failed: " + e.getMessage() + "</h3>");
            e.printStackTrace(out);
        }
    }
}