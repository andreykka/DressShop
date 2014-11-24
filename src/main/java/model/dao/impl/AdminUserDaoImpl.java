package model.dao.impl;

import java.sql.SQLException;
import java.util.List;

import model.dao.AdminUserDao;
import model.entity.AdminUser;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import model.util.HibernateUtil;
import org.hibernate.criterion.Restrictions;

public class AdminUserDaoImpl implements AdminUserDao {

    private static final Logger LOGGER = Logger.getLogger(AdminUserDaoImpl.class);

    public boolean getIsAdmin(String login, String password) throws SQLException{

        Session session = null;
        List list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            AdminUser admin = new AdminUser();
            admin.setLogin(login);
            admin.setPassword(password);
            list = session.createCriteria(AdminUser.class)
                    .add(Restrictions.eq("login", login))
                    .add(Restrictions.eq("password", password)).list();

        } catch (Exception e){
            LOGGER.error(e);
        }

        if (session != null && session.isOpen()){
            session.close();
        }

        return (list!=null && list.size() > 0);
    }

}
