package model.dao.impl;

import model.dao.ImagesDAO;
import model.entity.Images;
import model.util.FactoryDao;
import model.util.FactoryDaoHibernate;
import model.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gandy on 23.11.14.
 */
public class ImagesDaoImpl implements ImagesDAO {

    private static final Logger LOGGER = Logger.getLogger(ImagesDaoImpl.class);


    @Override
    public List<Images> getByIdGood(int id) throws SQLException {
        Session session = null;
        List<Images> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(Images.class).add(Expression.like("good.id", id)).list();
        }catch (Exception e) {
            LOGGER.error(e);
        }
        if (session != null && session.isOpen())
            session.close();

        return list;
    }

    @Override
    public int add(Images image) throws SQLException {
        Session session = null;
        int id = -1;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(image);
            session.getTransaction().commit();
            id = image.getId();
        } catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
        return id;
    }

    @Override
    public List<Images> get() throws SQLException {
        Session session = null;
        List<Images> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(Images.class).list();
        }catch (Exception e) {
            LOGGER.error(e);
        }
        if (session != null && session.isOpen())
            session.close();

        return list;
    }

    @Override
    public Images getById(int idImage) throws SQLException {
        Images image = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            image = (Images) session.get(Images.class, idImage);
            session.getTransaction().commit();

        }catch (Exception e){
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();

        return image;
    }

    @Override
    public void update(Images newImg) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(newImg);
            session.getTransaction().commit();
        }catch (Exception e){
            LOGGER.error(e);
        }
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void remove(Images image) throws SQLException {

        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(image);
            session.getTransaction().commit();
        }catch (Exception e){
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void remove(int id) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Images image = (Images) session.get(Images.class,id);
            session.delete(image);
            session.getTransaction().commit();

        }catch (Exception e){
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
    }
}
