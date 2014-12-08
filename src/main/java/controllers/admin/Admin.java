package controllers.admin;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gandy on 22.10.14.
 *
 */

@WebServlet(name = "Admin", urlPatterns = {"/admin", "/admin/"})
public class Admin extends HttpServlet {

    public static final String URL              = "/dressshop";

    public static       String ERROR_MESSAGE    = "";

    public static final String ADMIN            = URL   + "/admin";
    public static final String LOGIN            = URL   + "/login";
    public static final String PNF              = URL   + "/404";

    public static final String ADD_GOOD         = ADMIN + "/goods/add";
    public static final String EDIT_GOOD        = ADMIN + "/goods/edit";
    public static final String REMOVE_GOOD      = ADMIN + "/goods/remove";
    public static final String LIST_GOOD        = ADMIN + "/goods";

    public static final String ADD_CAT          = ADMIN + "/category/add";
    public static final String EDIT_CAT         = ADMIN + "/category/edit";
    public static final String REMOVE_CAT       = ADMIN + "/category/remove";
    public static final String LIST_CAT         = ADMIN + "/category";

    public static final String LOGIN_JSP        = "/WEB-INF/view/login.jsp";
    public static final String PNF_JSP          = "/WEB-INF/view/404.jsp";
    public static final String ADMIN_JSP        = "/WEB-INF/view/admin/admin.jsp";
    public static final String ADD_GOOD_JSP     = "/WEB-INF/view/admin/goods/add.jsp";
    public static final String EDIT_GOOD_JSP    = "/WEB-INF/view/admin/goods/edit.jsp";
    public static final String REMOVE_GOOD_JSP  = "/WEB-INF/view/admin/goods/remove.jsp";
    public static final String LIST_GOODS_JSP   = "/WEB-INF/view/admin/goods/list.jsp";
    public static final String LIST_CAT_JSP     = "/WEB-INF/view/admin/category/list.jsp";
    public static final String ADD_EDIT_CAT_JSP = "/WEB-INF/view/admin/category/addEdit.jsp";

    private static final Logger LOGGER = Logger.getLogger(Admin.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("ERROR_MESSAGE", ERROR_MESSAGE);
        req.getServletContext().getRequestDispatcher(ADMIN_JSP).forward(req,resp);
    }

}
