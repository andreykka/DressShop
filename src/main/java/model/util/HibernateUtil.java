package model.util;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();

	private HibernateUtil() { }

    private static SessionFactory buildSessionFactory(){

        SessionFactory sessionFac = null;
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFac = configuration.buildSessionFactory(builder.build());
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
        return sessionFac;
    }

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
