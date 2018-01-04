package by.bsuir.stock.dao;

import by.bsuir.stock.bean.CargosininvoiceEntity;
import by.bsuir.stock.bean.Shell;
import by.bsuir.stock.conection.ConnectionDB;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class CargosininvoiceDao implements InterfaceDao {

    CUDFunction<CargosininvoiceEntity> function = new CUDFunction<CargosininvoiceEntity>();

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
    public ArrayList<CargosininvoiceEntity> getList() {
        EntityManager entityManager = null;
        List list = new ArrayList<Shell>();
        try {
            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            list=entityManager.createQuery("from CargosininvoiceEntity stock").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return (ArrayList<CargosininvoiceEntity>) list;
    }

    @Override
    public CargosininvoiceEntity getEntity(Integer id) {
        EntityManager entityManager = null;
        CargosininvoiceEntity cargo = null;
        try {

            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            entityManager.getTransaction().begin();
            cargo =  entityManager.find(CargosininvoiceEntity.class, id);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return cargo;
    }
}
