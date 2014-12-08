package controllers.admin.goods;

import controllers.admin.Admin;
import model.dao.CategoryDAO;
import model.dao.GoodsDao;
import model.dao.ImagesDAO;
import model.dao.impl.GoodsDaoImpl;
import model.entity.Categories;
import model.entity.Goods;
import model.entity.Images;
import model.util.FactoryDaoHibernate;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

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


@WebServlet(name = "AddEditGood", urlPatterns = {   "/admin/goods/add", "/admin/goods/add/",
                                                    "/admin/goods/edit", "/admin/goods/edit/"})
public class AddEditGood extends HttpServlet{

    private static final String G_NAME          = "nameG";
    private static final String G_DESC          = "descG";
    private static final String G_COUNT         = "countG";
    private static final String G_PRICE         = "priceG";
    private static final String G_CATEGORY      = "categoryG";
    private static final String G_META_TITLE    = "MTitleG";
    private static final String G_META_DESC     = "MDescG";
    private static final String G_META_KEY      = "MKeyG";

    private static final String GOOD_IMG_PATH   = "/images/goods/";

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

    private static final String ACTION_ADD  = "add".toLowerCase();
    private static final String ACTION_EDIT = "edit".toLowerCase();

    private static final Logger LOGGER = Logger.getLogger(AddEditGood.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("In AddEditGood DOGET()");
        CategoryDAO categoryDAO = FactoryDaoHibernate.getInstance().getCategoryDAO();
        List<Categories> cats = null;
        try {
            cats = categoryDAO.getCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("category", cats);

        // slit the URL on sections
        String[] strings = req.getRequestURI().toLowerCase().split("/");

        String action = strings[strings.length-1].toLowerCase();

        String page = "";

        if (action.equals(ACTION_ADD)) {
            page = Admin.ADD_GOOD_JSP;
        } else if (action.equals(ACTION_EDIT)) {
            page = Admin.EDIT_GOOD_JSP;
            String id = req.getParameter("id");
            int idGood = 0;
            if (id != null) {
                try { idGood = Integer.parseInt(id); } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendRedirect(Admin.LIST_GOOD);
                }
            }
            GoodsDao goodsDao = new GoodsDaoImpl();
            try {
                Goods good  = goodsDao.getById(idGood);
                req.setAttribute("good", good);
            } catch (SQLException e) {
                Admin.ERROR_MESSAGE = "CAN`T READ FROM Goods. Please check connection with DB";
                e.printStackTrace();
            }
        } else {
            resp.sendRedirect(Admin.LIST_GOOD);
        }

        req.getServletContext().getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
        factory.setRepository(tempDir);

//        System.out.println("PATH= " + tempDir.getAbsoluteFile());
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
        Categories  category    = new Categories();


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


        // slit the URL on sections
        String[] strings = req.getRequestURI().toLowerCase().split("/");

        String action = strings[strings.length-1].toLowerCase();

        if (action.equals(ACTION_ADD)) {
            try {
                goodsDao.add(good);
                images.setGood(good);
                images.setUrl(gImgUrl);
                imagesDAO.add(images);
            } catch (SQLException e) {
                e.printStackTrace();
                Admin.ERROR_MESSAGE = "Can`t add good";
            }
        } else if (action.equals(ACTION_EDIT)) {
            String id = req.getParameter("id");
            int idGood = 0;
            if (id != null) {
                try { idGood = Integer.parseInt(id); } catch (Exception e) {
                    e.printStackTrace();
                    Admin.ERROR_MESSAGE = "id is not a Integer";
                    resp.sendRedirect(Admin.LIST_GOOD);
                }
            }
            try{
                Goods isEditGood = goodsDao.getById(idGood);
                if (isEditGood == null){
                    Admin.ERROR_MESSAGE = "Good with id '" + idGood +"' not contains in database";
                    resp.sendRedirect(Admin.LIST_GOOD);
                }
                good.setId(idGood);
                goodsDao.update(good);

                List<Images> isSetImages = imagesDAO.getByIdGood(good.getId());
                if (isSetImages != null && isSetImages.size() > 0){
                    for (Images img: isSetImages){
                        File file = new File(getServletContext().getRealPath(img.getUrl()));
                        if (file.exists()){
                            file.setWritable(true);
                            file.delete();
                        }
                        img.setUrl(gImgUrl);
                        imagesDAO.update(img);
                    }
                } else {
                    Images newImage = new Images();
                    newImage.setGood(good);
                    newImage.setUrl(gImgUrl);
                    imagesDAO.add(newImage) ;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                Admin.ERROR_MESSAGE = "Can`t add good";
            }
        }
        resp.sendRedirect(Admin.LIST_GOOD);
    }

    private void processUploadFile(FileItem item) {

        File fileUpload = null;
        File dir = null;
        Random rand = new Random();
        String fileName = "";
        //выбираем файлу имя пока не найдём свободное
        do {
            fileName = rand.nextInt() + item.getName();

            String path = getServletContext().getRealPath("/images/goods/" + fileName);
            System.out.println("PATH = "  + path);
            LOGGER.info("PATH = "  + path);
            fileUpload = new File(path);
            dir = new File(fileUpload.getPath());

            gImgUrl     = GOOD_IMG_PATH + fileName;

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
