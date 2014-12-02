package model.dao.impl;

import model.dao.GoodsDao;
import model.entity.Goods;
import model.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gandy on 27.10.14.
 *
 */
public class GoodsDaoImpl implements GoodsDao {

    private static final Logger LOGGER = Logger.getLogger(GoodsDaoImpl.class.toString());

    @Override
    public int add(Goods good) throws SQLException {
        Session session = null;
        int id = -1;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(good);
            session.getTransaction().commit();
            id = good.getId();
        }catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
        return id;
    }

    @Override
    public List<Goods> get() throws SQLException {
        Session session = null;
        List<Goods> list = new ArrayList<Goods>();

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createCriteria(Goods.class).list();
        }catch (Exception e) {
            LOGGER.error(e);
        }
        if (session != null && session.isOpen())
            session.close();

        return list;
    }

    @Override
    public Goods getById(int id) throws SQLException {
        Session session = null;
        Goods good = null;

        try {
            session =  HibernateUtil.getSessionFactory().openSession();
            good = (Goods) session.get(Goods.class, id);
        } catch (Exception e) {
           LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();

        return good;
    }

    @Override
    public void remove(Goods good) throws SQLException {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(good);
            session.getTransaction().commit();
        }catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void remove(int id) throws SQLException {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Goods good = (Goods) session.load(Goods.class, id);
            session.delete(good);
            session.getTransaction().commit();
        }catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void update(Goods newGood) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(newGood);
            session.getTransaction().commit();
        }catch (Exception e) {
            LOGGER.error(e);
        }

        if (session != null && session.isOpen())
            session.close();
    }

}
