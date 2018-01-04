package by.bsuir.stock.conection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConnectionDB {

    private ConnectionDB(){}

    private static final EntityManagerFactory sessionFactory;

    static {
        sessionFactory = Persistence.createEntityManagerFactory("HIBERNATE_PERSISTENCE");
    }

    public static EntityManagerFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
