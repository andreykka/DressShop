package controllers.admin;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by gandy on 22.10.14.
 *
 */

@WebServlet(name = "Admin", urlPatterns = "/admin")
public class Admin extends HttpServlet {


    public static final String URL                  = "/dressshop";

    public static final String ADMIN                = URL + "/admin";
    public static final String LOGIN                = URL + "/login";
    public static final String PNF                  = URL + "/404";
    public static final String ADD_TOVAR            = URL + ADMIN + "/addTovar";
    public static final String EDIT_TOVAR           = URL + ADMIN + "/editTovar";
    public static final String REMOVE_TOVAR         = URL + ADMIN + "/removeTovar";

    public static final String ADMIN_JSP            = "/WEB-INF/view/admin/admin.jsp";
    public static final String LOGIN_JSP            = "/WEB-INF/view/login.jsp";
    public static final String PNF_JSP              = "/WEB-INF/view/404.jsp";
    public static final String ADD_TOVAR_JSP        = "/WEB-INF/view/admin/goods/addTovar.jsp";
    public static final String EDIT_TOVAR_JSP       = "/WEB-INF/view/admin/goods/editTovar.jsp";
    public static final String REMOVE_TOVAR_JSP     = "/WEB-INF/view/admin/goods/removeTovar.jsp";

    private static final Logger LOGGER = Logger.getLogger(Admin.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("in Admin class");

        Map<String, String[]> map = req.getParameterMap();

        if (map != null && map.size() > 0) {
            String action = map.get("action")[0];

            if (action != null && !action.isEmpty()) {
                if (action.equalsIgnoreCase(ADD_TOVAR)  ||
                    action.equalsIgnoreCase(EDIT_TOVAR) ||
                    action.equalsIgnoreCase(REMOVE_TOVAR)) {

                    getServletContext().getRequestDispatcher(action).forward(req, resp);
                    return;
                }
            }
        }
        System.out.println("show JSP");
//        resp.sendRedirect(ADMIN_JSP);
        req.getServletContext().getRequestDispatcher(ADMIN_JSP).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOGGER.info("in Admin doPost");

    }

}
