package model.util;


import model.dao.AdminUserDao;
import model.dao.CategoryDAO;
import model.dao.GoodsDao;
import model.dao.ImagesDAO;
import model.dao.impl.AdminUserDaoImpl;
import model.dao.impl.CategoryDAOImpl;
import model.dao.impl.GoodsDaoImpl;
import model.dao.impl.ImagesDaoImpl;
import model.entity.Images;

public class FactoryDaoHibernate  implements FactoryDao{

    private static FactoryDaoHibernate instance; 
    
    private AdminUserDao    adminDao;
    private GoodsDao        goodsDao;
    private CategoryDAO     categoryDAO;
    private ImagesDAO       imagesDAO;
    
    private FactoryDaoHibernate() { }
    
    public static FactoryDaoHibernate getInstance(){
        if (instance == null){
            instance = new FactoryDaoHibernate();
        }
        return instance;
    }
    
    public AdminUserDao getAdminUserDao() {
        if (adminDao == null) {
            adminDao = new AdminUserDaoImpl();
        }
	    return adminDao;
    }

    public GoodsDao getGoodsDao() {
        if (goodsDao == null) {
            goodsDao = new GoodsDaoImpl();
        }
        return goodsDao;
    }

    public CategoryDAO getCategoryDAO() {
        if (categoryDAO == null) {
            categoryDAO = new CategoryDAOImpl();
        }
        return categoryDAO;
    }

    public ImagesDAO getImagesDAO() {
        if (imagesDAO == null){
            imagesDAO = new ImagesDaoImpl();
        }
        return imagesDAO;
    }
}
