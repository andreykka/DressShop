package controllers.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gandy on 27.10.14.
 *
 */
@WebServlet(name = "logout", urlPatterns = "/logout")
public class Logout extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resource = "/admin";

        if (req.getSession().getAttribute("admin") != null){
            req.getSession().removeAttribute("admin");
        }

        getServletContext().getRequestDispatcher(resource).forward(req,resp);

    }
}
