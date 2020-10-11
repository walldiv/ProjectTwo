package com.ex.databaseUtils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/***
 * This class is to configure hibernate and setup the connection to the database.
 *
 * @author Daniel Wallace
 * @author Jordan Severance
 * @author Shawyn Kane
 */

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://" + System.getenv("POSTGRES_URL") + ":" + System.getenv("POSTGRES_PORT") + "/" +  System.getenv("POSTGRES_DATABASE_NAME"));
            cfg.setProperty("hibernate.connection.username", System.getenv("POSTGRES_USERNAME"));
            cfg.setProperty("hibernate.connection.password", System.getenv("POSTGRES_PASSWORD"));
            cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL81Dialect");
            cfg.setProperty("hibernate.show_sql", "true");
            cfg.setProperty("hibernate.format_sql", "true");
            cfg.setProperty("hibernate.hbm2ddl.auto", "update");
            cfg.setProperty("hibernate.connection.pool_size", "50");
            cfg.setProperty("hibernate.current_session_context_class", "thread");
            return cfg.configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
