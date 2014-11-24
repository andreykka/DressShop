package model.dao.impl;

import model.dao.CategoryDAO;
import model.entity.Categories;
import model.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gandy on 18.11.14.
 */
public class CategoryDAOImpl implements CategoryDAO {

    private static final Logger LOGGER = Logger.getLogger(CategoryDAOImpl.class.toString());


    @Override
    public List<Categories> getCategories() {

        Session session = null;
        List<Categories> list = new ArrayList<Categories>();

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(Categories.class).list();
        }catch (Exception e) {
            LOGGER.error(e);
        }
        if (session != null && session.isOpen())
            session.close();

        return list;
    }

    @Override
    public Categories getCategoryById(int id) {
        Session session = null;
        Categories category = null;

        try {
            session =  HibernateUtil.getSessionFactory().openSession();
            category = (Categories) session.get(Categories.class, id);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();

        return category;
    }

    @Override
    public void removeCategory(Categories category) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(category);
            session.getTransaction().commit();
        }catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void removeCategory(int idCategory) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Categories category = (Categories) session.load(Categories.class, idCategory);
            session.delete(category);
            session.getTransaction().commit();
        }catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void updateCategory(Categories newCategory) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(newCategory);
            session.getTransaction().commit();
        }catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void addCategory(Categories category) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        }catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();

    }
}
