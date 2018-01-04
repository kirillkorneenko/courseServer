package by.bsuir.stock.dao;

import by.bsuir.stock.bean.Shell;

import java.util.ArrayList;


public interface InterfaceDao {

    boolean add(Shell entity);

    boolean delete(Shell entity);

    boolean update(Shell entity);

    ArrayList getList();

    Shell getEntity(Integer id);

}
