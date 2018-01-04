package by.bsuir.stock.dao;

import by.bsuir.stock.bean.InvoiceEntity;
import by.bsuir.stock.bean.Shell;
import by.bsuir.stock.conection.ConnectionDB;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDao implements InterfaceDao {
    CUDFunction<InvoiceEntity> function = new CUDFunction<InvoiceEntity>();

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
    public ArrayList<InvoiceEntity> getList() {
        EntityManager entityManager = null;
        List list = new ArrayList<Shell>();
        try {
            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            list=entityManager.createQuery("from InvoiceEntity stock").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return (ArrayList<InvoiceEntity>) list;
    }

    @Override
    public InvoiceEntity getEntity(Integer id) {
        EntityManager entityManager = null;
        InvoiceEntity invoice = null;
        try {

            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            entityManager.getTransaction().begin();
            invoice =  entityManager.find(InvoiceEntity.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return invoice;
    }
}
