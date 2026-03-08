package com.food;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_order_db", "root", "yashil@7");
            PreparedStatement ps = con.prepareStatement("SELECT name, email FROM users WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("user_name", rs.getString("name"));
                session.setAttribute("user_email", rs.getString("email"));
                response.sendRedirect("menu.html");
            } else {
                out.print("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.print("<div class='container mt-5 text-center'><div class='alert alert-danger shadow'>");
                out.print("<h3>Invalid Credentials!</h3><a href='login.html' class='btn btn-danger mt-2'>Try Again</a>");
                out.print("</div></div>");
            }
            con.close();
        } catch (Exception e) {
            out.print("Database Error: " + e.getMessage());
        }
    }
}