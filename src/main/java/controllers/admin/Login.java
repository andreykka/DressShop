package controllers.admin;

import config.Config;
import model.dao.AdminUserDao;
import model.util.FactoryDaoHibernate;
import org.apache.log4j.jmx.AbstractDynamicMBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "login page contoller", urlPatterns = {"/admin/login", "/admin/login/"}, name = "Login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 4142359428080050655L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Admin.LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = Config.getPasswordHash(req.getParameter("password"));

        AdminUserDao admin = FactoryDaoHibernate.getInstance().getAdminUserDao();

        Boolean isAdmin = false;
        try {
            isAdmin = admin.getIsAdmin(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no data from ADMIN_USERS");
        }
        String script;
        if (isAdmin){
            req.getSession().setAttribute("admin", login);
            req.setAttribute("isAdmin", true);
            resp.sendRedirect(Admin.LIST_GOOD);
//            getServletContext().getRequestDispatcher(Admin.LIST_GOODS_JSP).forward(req, resp);
        } else {
            script = "setTimeout('location.replace(\"http://" + Config.URL + "/dressshop/login" + "\")',500)";

            PrintWriter writer = resp.getWriter();
            writer.write("<html> <head> <script  type=\"application/javascript\" >" + script +
                    " </script></head><body>");
            writer.write("</br><div style=\"text-align: center;\">user: " + login + "</div>");
            writer.write("</br><div style=\"text-align: center\">pass: " + password+"</div>");

            writer.write("<h1 style=\"text-align: center;\">User with this login and password is not admin!!!</h1>");
            writer.write("</body> </html>");
        }


    }

}
