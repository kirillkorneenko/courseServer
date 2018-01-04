package by.bsuir.stock.service;

import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.bean.StockEntity;
import by.bsuir.stock.dao.StockDao;

import java.util.ArrayList;

public class StockService implements Service {

    StockDao dao = new StockDao();

    @Override
    public Batch add(Batch batch) {
        StockEntity stock = (StockEntity) batch.getDate();
        Batch result = new Batch();
        result.setResult(dao.add(stock));
        if (result.getResult()) {
            ArrayList<StockEntity> stockEntities = dao.getList();
            for (StockEntity st : stockEntities) {
                if (st.getLocation().equals(stock.getLocation()) && st.getMaxAmount() == stock.getMaxAmount()) {
                    result.setDate(st);
                    break;
                }
            }
        }
        return result;

    }

    @Override
    public Batch delete(Batch batch) {
        StockEntity cargo = (StockEntity) batch.getDate();
        Batch result = new Batch();
        result.setResult(dao.delete(cargo));

        return result;
    }

    @Override
    public Batch update(Batch batch) {
        StockEntity stock = (StockEntity) batch.getDate();
        Batch result = new Batch();
        result.setResult(dao.update(stock));
        return result;
    }

    @Override
    public Batch getList() {
        ArrayList<StockEntity> list = dao.getList();
        Batch result = new Batch();
        result.setList(list);
        result.setResult(true);
        return result;
    }

    @Override
    public Batch getEntity(Batch batch) {
        StockEntity cargo = (StockEntity) batch.getDate();
        Batch result = new Batch();
        result.setDate(dao.getEntity(cargo.getId()));
        result.setResult(true);
        return result;
    }

    public Batch deleteStocks(Batch batch) {
        ArrayList<StockEntity> list = (ArrayList<StockEntity>) batch.getList();
        Batch result = new Batch();
        result.setResult(true);
        for (StockEntity stock : list) {
            if (!dao.delete(stock)) {
                result.setResult(false);
            }
        }
        return result;
    }
}
