package by.bsuir.stock.service;

import by.bsuir.stock.bean.Batch;

public interface Service {

        Batch add(Batch batch);

        Batch delete(Batch batch);

        Batch update(Batch batch);

        Batch getList();

        Batch getEntity(Batch batch);

}
