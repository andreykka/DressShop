package controllers.admin.category;

import controllers.admin.Admin;
import model.dao.CategoryDAO;
import model.dao.GoodsDao;
import model.dao.impl.CategoryDAOImpl;
import model.dao.impl.GoodsDaoImpl;
import model.entity.Categories;
import model.entity.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gandy on 03.12.14.
 *
 */

@WebServlet(name = "ListCat", urlPatterns = {"/admin/category",  "/admin/category/"})
public class ListCat extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryDAO categoryDAO = new CategoryDAOImpl();
        try {
            List<Categories> categories  = categoryDAO.getCategories();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher(Admin.LIST_CAT_JSP).forward(req, resp);
        } catch (SQLException e) {
            Admin.ERROR_MESSAGE = "CAN`T READ FROM Goods. Please check connection with DB";
            e.printStackTrace();
        }

    }
}
