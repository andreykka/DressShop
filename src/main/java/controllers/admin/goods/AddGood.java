package controllers.admin.goods;

import controllers.admin.Admin;
import model.dao.CategoryDAO;
import model.dao.GoodsDao;
import model.dao.ImagesDAO;
import model.entity.Categories;
import model.entity.Goods;
import model.entity.Images;
import model.util.FactoryDaoHibernate;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.imageio.IIOImage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.*;


@WebServlet(name = "AddGood", urlPatterns = "/admin/goods/add")
public class AddGood extends HttpServlet{

    private static final String G_NAME          = "nameG";
    private static final String G_DESC          = "descG";
    private static final String G_COUNT         = "countG";
    private static final String G_PRICE         = "priceG";
    private static final String G_CATEGORY      = "categoryG";
    private static final String G_META_TITLE    = "MTitleG";
    private static final String G_META_DESC     = "MDescG";
    private static final String G_META_KEY      = "MKeyG";

    private static final Charset ISO_8859_1     =  Charset.forName("ISO-8859-1");
    private static final Charset UTF_8          =  Charset.forName("UTF-8");

    private String  gName;
    private String  gDesc;
    private int     gIdCat;
    private int     gCount;
    private double  gPrice;
    private String  gMTitle;
    private String  gMDesc;
    private String  gMKey;
    private String  gImgUrl;

    private static ArrayList<Map>   categories = new ArrayList<Map>();

    private static final Logger LOGGER = Logger.getLogger(AddGood.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("In AddGood DOGET()");
        CategoryDAO categoryDAO = FactoryDaoHibernate.getInstance().getCategoryDAO();
        List<Categories> cats = null;
        try {
            cats = categoryDAO.getCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        categories.clear();
        for (Categories cat: cats) {
            Map map = new HashMap();
            map.put("id", cat.getId());
            map.put("name",cat.getName());
            categories.add(map);
            System.out.println("id=" + cat.getId() + "  name=" + cat.getName());
        }
        req.setAttribute("categories", categories);

        req.getServletContext().getRequestDispatcher(Admin.ADD_TOVAR_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("BEFORE encoding =" + req.getCharacterEncoding());
        req.setCharacterEncoding("UTF-8");
        System.out.println("encoding =" + req.getCharacterEncoding());
        // check is multipart content datea
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            LOGGER.error("ENCRIPTION is not multipart data");
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Максимальный буфера данных в байтах,
        // при его привышении данные начнут записываться на диск во временную директорию
        // устанавливаем один мегабайт
        factory.setSizeThreshold(1024*1024);

        File tempDir  = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");

        System.out.println("PATH= " + tempDir.getAbsoluteFile());

        factory.setRepository(tempDir);

        //Создаём сам загрузчик
        ServletFileUpload upload = new ServletFileUpload(factory);

        //максимальный размер данных который разрешено загружать в байтах
        //по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
        upload.setSizeMax(1024 * 1024 * 10);

        try {
            List items = upload.parseRequest(req);
            Iterator iterator = items.iterator();

            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                if (item.isFormField()) {
                    proccessField(item);
                } else {
                    processUploadFile(item);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        GoodsDao    goodsDao    = FactoryDaoHibernate.getInstance().getGoodsDao();
        ImagesDAO   imagesDAO   = FactoryDaoHibernate.getInstance().getImagesDAO();
        CategoryDAO categoryDAO = FactoryDaoHibernate.getInstance().getCategoryDAO();
        Goods       good        = new Goods();
        Images      images      = new Images();
        Categories  category   = new Categories();

        System.out.println("idCat= " + gIdCat);

        good.setName(this.gName);
        good.setDescription(this.gDesc);
        try {
            good.setCategory(categoryDAO.getCategoryById(gIdCat));
        } catch (SQLException e) {e.printStackTrace();}
        good.setCountAvailable(this.gCount);
        good.setCountSold(0);
        good.setPrice(this.gPrice);
        good.setMetaTitle(this.gMTitle);
        good.setMetaDescription(this.gMDesc);
        good.setMetaKeywords(this.gMKey);


        try {
            goodsDao.add(good);

            images.setGood(good);
            images.setUrl(gImgUrl);
            imagesDAO.add(images);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(Admin.ADMIN);

    }

    private void processUploadFile(FileItem item) {

        File fileUpload = null;
        File dir = null;
        Random rand = new Random();
        String fileName = "";
        //выбираем файлу имя пока не найдём свободное
        do {
            fileName = rand.nextInt() + item.getName();
            String path = getServletContext().getRealPath("/images/goods/" + fileName );
            System.out.println("PATH = "  + path);
            LOGGER.info("PATH = "  + path);
            fileUpload = new File(path);
            dir = new File(fileUpload.getPath());

            gImgUrl     = path;

        }while (fileUpload.exists());

        // створюємо файл та записуємо в нього
        try {
            if (!dir.exists()) {
                dir.createNewFile();
            }
            fileUpload.createNewFile();
            item.write(fileUpload);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void proccessField(FileItem item) {

        String castedToCharsetField = new String(item.getString().getBytes(ISO_8859_1), UTF_8);

        switch (item.getFieldName()) {
            case G_NAME:        { gName     = castedToCharsetField; break;}
            case G_DESC:        { gDesc     = castedToCharsetField; break;}
            case G_CATEGORY:    { gIdCat    = Integer.parseInt(castedToCharsetField);   break;}
            case G_COUNT:       { gCount    = Integer.parseInt(castedToCharsetField);   break;}
            case G_PRICE:       { gPrice    = Double.parseDouble(castedToCharsetField); break;}
            case G_META_TITLE:  { gMTitle   = castedToCharsetField; break;}
            case G_META_DESC:   { gMDesc    = castedToCharsetField; break;}
            case G_META_KEY:    { gMKey     = castedToCharsetField; break;}
        }
    }

}
