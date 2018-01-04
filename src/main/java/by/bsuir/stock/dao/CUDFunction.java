package by.bsuir.stock.dao;

import by.bsuir.stock.bean.Shell;
import by.bsuir.stock.conection.ConnectionDB;
import org.hibernate.Session;

import javax.persistence.EntityManager;

public class CUDFunction<T extends Shell> {

    public boolean add(Shell entity) {
        EntityManager entityManager = null;
        T elem = (T) entity;
        try {
            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(elem);
            entityManager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


    public boolean delete(Shell entity) {
        EntityManager entityManager =null;
        T elem = (T) entity;
        try {
            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(elem) ? elem : entityManager.merge(elem));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }


    public boolean update(Shell entity) {
        EntityManager entityManager =null;
        T elem = (T) entity;
        try {
           entityManager = ConnectionDB.getSessionFactory().createEntityManager();
           entityManager.getTransaction().begin();
           entityManager.merge(elem);
           entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

}
