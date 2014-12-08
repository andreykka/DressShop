package controllers.admin.goods;

import controllers.admin.Admin;
import model.dao.GoodsDao;
import model.dao.ImagesDAO;
import model.dao.impl.GoodsDaoImpl;
import model.dao.impl.ImagesDaoImpl;
import model.entity.Goods;
import model.entity.Images;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by gandy on 23.11.14.
 *
 */
@WebServlet(name = "removeServlet", urlPatterns = "/admin/goods/remove")
public class RemoveGood extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param = req.getParameter("id");
        try{
            int idGood = Integer.parseInt(param);
            GoodsDao    goodsDao    = new GoodsDaoImpl();
            ImagesDAO   imagesDAO   = new ImagesDaoImpl();
            Goods good = goodsDao.getById(idGood);
            if (good != null) {
                List<Images> imagesList = imagesDAO.getByIdGood(good.getId());
                // remove all images relations with good
                if (imagesList != null && imagesList.size() > 0) {
                    for (Images img: imagesList){
                        File fileImg = new File(getServletContext().getRealPath(img.getUrl()));
                        if (fileImg.exists()) {
                            fileImg.setWritable(true);
                            fileImg.delete();
                        }
                        imagesDAO.remove(img);
                    }
                }
                goodsDao.remove(good);
            }
        } catch (Exception e) {
            req.setAttribute("ERROR_MESSAGE", "id is not a INTEGER");
            req.getServletContext().getRequestDispatcher(Admin.URL + Admin.LIST_GOOD).forward(req, resp);
            //resp.sendRedirect(Admin.LIST_GOOD);
        }

        req.setAttribute("INFO_MESSAGE", "goot with id=" + param + " was remove successful");
        req.getServletContext().getNamedDispatcher("ListGoods").forward(req, resp);
        //req.getServletContext().getRequestDispatcher(Admin.URL + Admin.LIST_GOOD).forward(req, resp);

        //resp.sendRedirect(Admin.LIST_GOOD);
    }


}
