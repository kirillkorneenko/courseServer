package by.bsuir.stock.service;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.UsersEntity;
import by.bsuir.stock.dao.UsersDao;
import org.omg.CORBA.UserException;

import java.util.ArrayList;

public class UsersService implements Service {

    UsersDao dao = new UsersDao();

    @Override
    public Batch add(Batch batch) {
        UsersEntity usersEntity = (UsersEntity) batch.getDate();
        Batch result = new Batch();
        result.setResult(dao.add(usersEntity));
        if (result.getResult()) {
            ArrayList<UsersEntity> usersEntities = dao.getList();
            for (UsersEntity us : usersEntities) {
                if (us.getLogin().equals(usersEntity.getLogin()) && us.getPassword().equals(usersEntity.getPassword())) {
                    result.setDate(us);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Batch delete(Batch batch) {
        UsersEntity usersEntity = (UsersEntity) batch.getDate();
        Batch result = new Batch();
        dao.delete(usersEntity);

        return null;
    }

    @Override
    public Batch update(Batch batch) {
        UsersEntity usersEntity = (UsersEntity) batch.getDate();
        Batch result = new Batch();
        dao.update(usersEntity);

        return null;
    }

    @Override
    public Batch getList() {
        ArrayList<UsersEntity> list = dao.getList();
        Batch result = new Batch();

        return null;
    }

    @Override
    public Batch getEntity(Batch batch) {
        UsersEntity usersEntity = (UsersEntity) batch.getDate();
        Batch result = new Batch();
        dao.getEntity(usersEntity.getId());

        return null;
    }

    public Batch autorization(Batch batch) {
        UsersEntity usersEntity = (UsersEntity) batch.getDate();
        Batch result = new Batch();
        ArrayList<UsersEntity> list = dao.getList();
        result.setResult(false);
        if (list.size() != 0) {
            for (UsersEntity us : list) {
                if (us.getLogin().equals(usersEntity.getLogin()) && us.getPassword().equals(usersEntity.getPassword())) {
                    result.setDate(us);
                    result.setResult(true);
                    break;
                }
            }
        } else {
            result.setResult(false);
        }
        return result;
    }
}
