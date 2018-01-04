package by.bsuir.stock.dao;

import by.bsuir.stock.bean.Shell;
import by.bsuir.stock.bean.UsersEntity;
import by.bsuir.stock.conection.ConnectionDB;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class UsersDao implements InterfaceDao {

    CUDFunction<UsersEntity> function = new CUDFunction<UsersEntity>();

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
    public ArrayList<UsersEntity> getList() {
        EntityManager entityManager = null;
        List list = new ArrayList<UsersEntity>();
        try {
            entityManager = ConnectionDB.getSessionFactory().createEntityManager();
            list=entityManager.createQuery("from UsersEntity stock").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return (ArrayList<UsersEntity>) list;
    }

    @Override
    public UsersEntity getEntity(Integer id) {
        Session session = null;
        //EntityManager entityManager= null;
        UsersEntity employees = null;
        try {
            //UsersEntity us= new UsersEntity();
           // entityManager= ConnectionDB.getSessionFactory().createEntityManager();
            //entityManager.createQuery("select UsersEntity from UsersEntity where login ="+us.getLogin()+" and password= "+us.getPassword()).getResultList();
            session = ConnectionDB.getSessionFactory().createEntityManager().unwrap(Session.class);
            employees =  session.load(UsersEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return employees;
    }
}
