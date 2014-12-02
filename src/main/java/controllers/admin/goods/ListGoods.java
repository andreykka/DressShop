package controllers.admin.goods;

import controllers.admin.Admin;
import model.dao.GoodsDao;
import model.dao.impl.GoodsDaoImpl;
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
 * Created by gandy on 23.11.14.
 *
 */

@WebServlet(name = "ListGoods", urlPatterns = {"/admin/goods", "/admin/goods/" })
public class ListGoods extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GoodsDao goodsDao = new GoodsDaoImpl();

        try {
            List<Goods> goods  = goodsDao.get();
            req.setAttribute("goods", goods);
            req.getRequestDispatcher(Admin.LIST_GOODS_JSP).forward(req, resp);
        } catch (SQLException e) {
            Admin.ERROR_MESSAGE = "CAN`T READ FROM Goods. Please check connection with DB";
            e.printStackTrace();
        }

    }
}
