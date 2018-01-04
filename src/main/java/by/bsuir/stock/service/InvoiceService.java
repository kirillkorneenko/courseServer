package by.bsuir.stock.service;

import by.bsuir.stock.bean.*;
import by.bsuir.stock.dao.CargoDao;
import by.bsuir.stock.dao.CargosininvoiceDao;
import by.bsuir.stock.dao.InvoiceDao;

import java.util.ArrayList;
import java.util.Collection;

public class InvoiceService implements Service  {

    InvoiceDao dao = new InvoiceDao();

    @Override
    public Batch add(Batch batch) {
        InvoiceEntity invoice = (InvoiceEntity) batch.getDate();
        Batch result = new Batch();
        result.setResult(dao.add(invoice));
        if(result.getResult()){
            ArrayList<InvoiceEntity> list = dao.getList();
            for (InvoiceEntity invoiceEntity :list) {
                if(invoice.getNumber()==invoiceEntity.getNumber()) result.setDate(invoiceEntity);
            }
        }

        return result;
    }

    @Override
    public Batch delete(Batch batch) {
        InvoiceEntity invoice = (InvoiceEntity) batch.getDate();
        Batch result = new Batch();
        result.setResult(dao.delete(invoice));
        return result;
    }

    @Override
    public Batch update(Batch batch) {
        InvoiceEntity invoice = (InvoiceEntity) batch.getDate();
        Batch result = new Batch();
        result.setResult(dao.update(invoice));
        Collection<CargosininvoiceEntity> listCollection =  invoice.getCargosininvoicesById();
        ArrayList<CargosininvoiceEntity>  list = new ArrayList<>();
        for (CargosininvoiceEntity cargosininvoiceEntity:listCollection){
            list.add(cargosininvoiceEntity);
        }

        for (int i = 0; i < list.size(); i++) {
            CargoEntity cargo = list.get(i).getCargoByIdCargo();
            cargo.setUsersByIdUser(invoice.getUsersByIdUser());
            CargoDao cargoDao = new CargoDao();
            cargoDao.update(cargo);
        }
        return result;
    }

    @Override
    public Batch getList() {
        ArrayList<InvoiceEntity> list = dao.getList();
        Batch result = new Batch();
        result.setList(dao.getList());
        result.setResult(true);
        return result;
    }

    @Override
    public Batch getEntity(Batch batch) {
        InvoiceEntity cargo = (InvoiceEntity) batch.getDate();
        Batch result = new Batch();
        InvoiceEntity invoice =dao.getEntity(cargo.getId());
        if(invoice==null){
            result.setResult(false);
        }
        else {
            result.setResult(true);
            result.setDate(invoice);
        }
        return result;
    }

    public Batch addCargo(Batch batch){
        CargosininvoiceDao daoCargo = new CargosininvoiceDao();
        ArrayList<CargosininvoiceEntity> list = (ArrayList<CargosininvoiceEntity>) batch.getList();
        Batch result = new Batch();
        result.setResult(true);
        for (CargosininvoiceEntity cargo : list) {
            if(!daoCargo.add(cargo)){
                result.setResult(false);
                break;
            }
        }
        return result;
    }

    public Batch getInvoiceByUser(Batch batch){
        UsersEntity user= (UsersEntity) batch.getDate();
        ArrayList<InvoiceEntity> list =dao.getList();
        ArrayList<InvoiceEntity> listResult = new ArrayList<>();
        Batch result = new Batch();
        result.setResult(true);
        for (InvoiceEntity invoice: list) {
            if (invoice.getUsersByIdUser().equals(user)){
                listResult.add(invoice);
            }
        }
        result.setList(listResult);
        return result;
    }
}
