package by.bsuir.stock.dao;

import by.bsuir.stock.bean.Shell;
import by.bsuir.stock.bean.StockEntity;
import by.bsuir.stock.conection.ConnectionDB;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class StockDao implements InterfaceDao {

    CUDFunction<StockEntity> function = new CUDFunction<StockEntity>();

    @Override
    public boolean add(Shell entity) {
        return function.add(entity);
    }

    @Override
    public boolean delete(Shell entity) {
        return function.delete(entity);
    }

    @Override
    public boolean update(Shell entity) {
        return function.update(entity);
    }

    @Override
    public ArrayList<StockEntity> getList() {
        EntityManager entityManager = null;
        List list = new ArrayList<StockEntity>();
        try {
            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            list=entityManager.createQuery("from StockEntity stock").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return (ArrayList<StockEntity>) list;
    }

    @Override
    public StockEntity getEntity(Integer id) {
        EntityManager entityManager = null;
        StockEntity stock = null;
        try {

            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            entityManager.getTransaction().begin();
            stock =  entityManager.find(StockEntity.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return stock;
    }
}
