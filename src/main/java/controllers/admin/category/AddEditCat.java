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
import java.nio.charset.Charset;
import java.sql.SQLException;

/**
 * Created by gandy on 03.12.14.
 *
 */

@WebServlet(name = "AddEditCat", urlPatterns = {"/admin/category/add",  "/admin/category/add/",
                                                "/admin/category/edit", "/admin/category/edit/"})
public class AddEditCat extends HttpServlet {


    private static final Charset ISO_8859_1     =  Charset.forName("ISO-8859-1");
    private static final Charset UTF_8          =  Charset.forName("UTF-8");

    private static final String CAT_NAME          = "nameCat";
    private static final String CAT_VALUE         = "valCat";
    private static final String CAT_META_TITLE    = "MTitleCat";
    private static final String CAT_META_DESC     = "MDescCat";
    private static final String CAT_META_KEY      = "MKeyCat";

    private String  catName;
    private String  catVal;
    private String  catMTitle;
    private String  catMDesc;
    private String  catMKey;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // slit the URL on sections
        String[] strings = req.getRequestURI().toLowerCase().split("/");

        String action = strings[strings.length-1].toLowerCase();
        boolean isEdit = action.equals("edit");

        if (isEdit) {
            req.setAttribute("pageTitle", "Редактирование категории");

            String id = req.getParameter("id");
            try {
                int idCat = Integer.parseInt(id);
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                Categories category = categoryDAO.getCategoryById(idCat);
                req.setAttribute("cat", category);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("pageTitle", "Добавление новой категории");

        }

        req.getRequestDispatcher(Admin.ADD_EDIT_CAT_JSP).forward(req, resp);
        req.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // slit the URL on sections
        String[] strings = req.getRequestURI().toLowerCase().split("/");

        String action = strings[strings.length-1].toLowerCase();
        boolean isEdit = action.equals("edit");

        CategoryDAO categoryDAO = new CategoryDAOImpl();
        Categories category;

        req.setCharacterEncoding("UTF-8");

        this.catName    = req.getParameter(CAT_NAME);
        this.catVal     = req.getParameter(CAT_VALUE);
        this.catMTitle  = req.getParameter(CAT_META_TITLE);
        this.catMKey    = req.getParameter(CAT_META_KEY);
        this.catMDesc   = req.getParameter(CAT_META_DESC);

        if (!isEdit) {
            category = new Categories();
            category.setName(this.catName);
            category.setValue(this.catVal);
            category.setMetaTitle(this.catMTitle);
            category.setMetaDescription(this.catMDesc);
            category.setMetaKeywords(this.catMKey);
            try {
                categoryDAO.addCategory(category);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String id = req.getParameter("id");
            try {
                int idCat = Integer.parseInt(id);
                category = categoryDAO.getCategoryById(idCat);

                category.setName(this.catName);
                category.setValue(this.catVal);
                category.setMetaTitle(this.catMTitle);
                category.setMetaDescription(this.catMDesc);
                category.setMetaKeywords(this.catMKey);

                categoryDAO.updateCategory(category);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect(Admin.LIST_CAT);
        //req.getRequestDispatcher(Admin.LIST_CAT).forward(req, resp);
    }
}
