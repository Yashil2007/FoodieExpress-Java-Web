<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Orders</title>
    <style>
        body { font-family: Arial; text-align: center; margin-top: 50px; background-color: #f4f4f4; }
        table { border-collapse: collapse; margin: auto; background-color: white; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #333; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        h2 { color: #333; }
        .error { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h2>All Customer Orders</h2>

    <table>
        <tr>
            <th>Order ID</th>
            <th>Restaurant</th>
            <th>Food Item</th>
            <th>Quantity</th>
            <th>Payment Mode</th>
            <th>Total Amount</th>
        </tr>

        <%
            try {
                // 1. Load Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 2. Establish Connection (Using your unified password)
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/food_order_db", "root", "yashil@7");

                // 3. Execute Query
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM orders ORDER BY id DESC");

                // 4. Loop through results
                while(rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("restaurant") %></td>
            <td><%= rs.getString("foodname") %></td>
            <td><%= rs.getInt("quantity") %></td>
            <td><%= rs.getString("paymentmode") %></td>
            <td>₹<%= rs.getInt("totalamount") %></td>
        </tr>
        <%
                }
                con.close(); // Always close connection
            } catch(Exception e) {
                out.println("<tr><td colspan='6' class='error'>Database Error: " + e.getMessage() + "</td></tr>");
            }
        %>
    </table>

    <br><br>
    <a href="menu.html" style="text-decoration: none; color: blue; font-weight: bold;">← Back to Menu</a>
</body>
</html>