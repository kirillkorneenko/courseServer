package by.bsuir.stock.dao;

import by.bsuir.stock.bean.CargoEntity;
import by.bsuir.stock.bean.Shell;
import by.bsuir.stock.conection.ConnectionDB;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CargoDao implements InterfaceDao {

    CUDFunction<CargoEntity> function = new CUDFunction<CargoEntity>();


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
    public ArrayList<CargoEntity> getList() {
        EntityManager entityManager = null;
        List list = new ArrayList<Shell>();
        try {
            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            list=entityManager.createQuery("from CargoEntity stock").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return (ArrayList<CargoEntity>) list;
    }

    @Override
    public CargoEntity getEntity(Integer id) {
        EntityManager entityManager = null;
        CargoEntity cargo = null;
        try {

            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            entityManager.getTransaction().begin();
            cargo =  entityManager.find(CargoEntity.class, id);
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

    public boolean addCargos( ArrayList<CargoEntity> list){
        EntityManager entityManager = null;
        try {
            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            entityManager.getTransaction().begin();

            for (CargoEntity cargo : list) {
                if(!add(cargo)){
                    return false;
                }
            }

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
