package controllers.admin.category;

import controllers.admin.Admin;
import model.dao.CategoryDAO;
import model.dao.impl.CategoryDAOImpl;
import model.entity.Categories;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gandy on 03.12.14.
 *
 */
@WebServlet(name = "RemoveCat", urlPatterns = {"/admin/category/remove", "/admin/category/remove/"})
public class RemoveCat extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("id");
        try {
            int idGood = Integer.parseInt(param);
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            Categories cat;
            categoryDAO.removeCategory(idGood);
            req.setAttribute("INFO_MESSAGE", "category with id=" + param + " was remove successful");
        } catch (Exception e) {
            req.setAttribute("ERROR_MESSAGE", "id is not a INTEGER");
          //  req.getServletContext().getRequestDispatcher(Admin.URL + Admin.LIST_CAT).forward(req, resp);
        }

        req.getServletContext().getNamedDispatcher("ListCat").forward(req, resp);
    }
}
