import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class WelcomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();

        if(cookies != null) {
            for(Cookie c : cookies) {
                if(c.getName().equals("user")) {
                    out.println("<h2>Welcome " + c.getValue() + "</h2>");
                }
            }
        } else {
            out.println("No cookies found");
        }
    }
}
