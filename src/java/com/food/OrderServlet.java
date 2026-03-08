package com.food;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.*;
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userName = (session != null) ? (String) session.getAttribute("user_name") : "Guest";
        String restaurant = request.getParameter("restaurant");
        String[] selectedFoods = request.getParameterValues("food");
        String payment = request.getParameter("payment");  
        String qtyStr = request.getParameter("quantity");
        int quantity = (qtyStr != null && !qtyStr.isEmpty()) ? Integer.parseInt(qtyStr) : 1;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (selectedFoods == null || selectedFoods.length == 0) {
            out.println("<script>alert('Please select at least one item!'); window.location='menu.html';</script>");
            return;
        }
        int grandTotal = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/food_order_db", "root", "yashil@7");
            out.println("<html><head><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'></head>");
            out.println("<body class='bg-light'><div class='container mt-5'><div class='card shadow p-4 mx-auto' style='max-width:600px;'>");
            out.println("<h2 class='text-center text-danger'>Order Receipt</h2><hr>");
            out.println("<p><b>Customer Name:</b> " + userName + "</p>"); // Displaying User Name
            out.println("<p><b>Restaurant:</b> " + restaurant + "</p>");
            out.println("<table class='table'><thead><tr><th>Item</th><th>Price</th><th>Qty</th><th>Subtotal</th></tr></thead><tbody>");

            for (String food : selectedFoods) {
                int price = 0;
                if (food.equalsIgnoreCase("Pizza")) price = 200;
                else if (food.equalsIgnoreCase("Garlic Bread")) price = 150;
                else if (food.equalsIgnoreCase("Choco Lava")) price = 100;
                else if (food.equalsIgnoreCase("Whopper")) price = 120;
                else if (food.equalsIgnoreCase("Fries")) price = 90;
                else if (food.equalsIgnoreCase("Veggie Strips")) price = 110;
                else if (food.equalsIgnoreCase("Paneer Sub")) price = 180;
                else if (food.equalsIgnoreCase("Veggie Sub")) price = 160;
                else if (food.equalsIgnoreCase("Coke") || food.equalsIgnoreCase("Pepsi") || food.equalsIgnoreCase("Sprite")) {
                    price = 60;
                }
                int subtotal = price * quantity;
                grandTotal += subtotal;
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO orders(user_name, restaurant, foodname, quantity, paymentmode, totalamount) VALUES(?,?,?,?,?,?)");
                ps.setString(1, userName);
                ps.setString(2, restaurant);
                ps.setString(3, food);
                ps.setInt(4, quantity);
                ps.setString(5, payment);
                ps.setInt(6, subtotal);
                ps.executeUpdate();
                out.println("<tr><td>" + food + "</td><td>₹" + price + "</td><td>" + quantity + "</td><td>₹" + subtotal + "</td></tr>");
            }
            out.println("</tbody></table>");
            out.println("<div class='text-end'><h3>Grand Total: ₹" + grandTotal + "</h3>");
            out.println("<p class='badge bg-primary' style='font-size:1rem;'>Payment Method: " + payment + "</p></div>");
            out.println("<hr><div class='text-center'><button class='btn btn-danger' onclick='window.print()'>Print Bill</button>");
            out.println("<a href='menu.html' class='btn btn-outline-secondary ms-2'>Back to Menu</a></div>");
            out.println("</div></div></body></html>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}