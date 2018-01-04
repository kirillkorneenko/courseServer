package by.bsuir.stock.service;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.CargoEntity;
import by.bsuir.stock.bean.StockEntity;
import by.bsuir.stock.dao.CargoDao;
import by.bsuir.stock.dao.StockDao;

import java.util.ArrayList;
import java.util.Collection;

public class CargoService implements Service {

    CargoDao dao= new CargoDao();

    @Override
    public Batch add(Batch batch) {
        CargoEntity cargo = (CargoEntity) batch.getDate();
        Batch result = new Batch();
        dao.add(cargo);

        return null;
    }

    @Override
    public Batch delete(Batch batch) {
        CargoEntity cargo = (CargoEntity) batch.getDate();
        Batch result = new Batch();
        result.setResult(dao.delete(cargo));
        return result;
    }

    @Override
    public Batch update(Batch batch) {
        CargoEntity cargo = (CargoEntity) batch.getDate();
        Batch result = new Batch();
        result.setResult(dao.update(cargo));
        return result;
    }

    @Override
    public Batch getList() {
        ArrayList<CargoEntity> list = dao.getList();
        Batch result = new Batch();
        result.setList(list);
        result.setResult(true);
        return result;
    }

    @Override
    public Batch getEntity(Batch batch) {
        CargoEntity cargo = (CargoEntity) batch.getDate();
        Batch result = new Batch();
        result.setDate(dao.getEntity(cargo.getId()));
        if (result.getDate()!=null){
            result.setResult(true);
        }
        else result.setResult(false);

        return result;
    }

    public Batch getListByStock(Batch batch){
        StockEntity stock = (StockEntity) batch.getDate();
        StockDao stockDao = new StockDao();
        StockEntity stockBD =stockDao.getEntity(stock.getId());
        Collection<CargoEntity> list = stockBD.getCargosById();
        ArrayList<CargoEntity> listCargo = new ArrayList<CargoEntity>();
        for (CargoEntity cargoEntity : list) {
            listCargo.add(cargoEntity);
        }
        Batch result = new Batch();
        result.setList(listCargo);
        result.setResult(true);
        return result;
    }

    public Batch addCargos(Batch batch){
        ArrayList<CargoEntity> list = (ArrayList<CargoEntity>) batch.getList();
        Batch result = new Batch();
        StockEntity stock = list.get(0).getStockByIdStock();
        StockDao stockDao = new StockDao();
        stock =stockDao.getEntity(stock.getId());
        boolean flag = true;
        double amount=stock.getCongestion();
        double maxAmount= stock.getMaxAmount();
        result.setResult(true);
        for (CargoEntity cargo: list) {
            amount+= cargo.getAmount();
        }

        if(maxAmount>= amount&& dao.addCargos(list)){
            stock.setCongestion(amount);
            stockDao.update(stock);
        }
        else result.setResult(false);
        return result;
    }

    public Batch updateCargos(Batch batch){
        ArrayList<CargoEntity> list = (ArrayList<CargoEntity>) batch.getList();
        Batch result =new Batch();
        result.setResult(true);
        for (CargoEntity cargo: list){
            if(!dao.update(cargo)){
                result.setResult(false);
            }
        }
        return result;
    }
}
