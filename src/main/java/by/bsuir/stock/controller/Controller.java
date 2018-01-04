package by.bsuir.stock.controller;


import by.bsuir.stock.bean.Batch;
import by.bsuir.stock.service.*;


public class Controller {

        private static Service  service= null;


        public static Batch execute(Batch batch){
                CargoService cargoService= new CargoService();
                InvoiceService invoiceService = new InvoiceService();
                String command = batch.getCommand();
                Batch result;
                switch (Commands.valueOf(command.toUpperCase())){
                        case ADD_CARGO :
                                service= new CargoService();
                                result= service.add(batch);
                                return result;
                        case ADD_CARGOS :
                                result= cargoService.addCargos(batch);
                                return result;
                        case DELETE_CARGO:
                                service= new CargoService();
                                result = service.delete(batch);
                                return result;
                        case UPDATE_CARGO:
                                service= new CargoService();
                                result= service.update(batch);
                                return result;
                        case GET_CARGO:
                                service= new CargoService();
                                result= service.getEntity(batch);
                                return result;
                        case GET_CARGO_LIST:
                                service= new CargoService();
                                result= service.getList();
                                return result;
                        case GET_CARGO_LIST_BY_STOCK:
                                result= cargoService.getListByStock(batch);
                                return result;
                        case UPDATE_CARGOS:
                                result=cargoService.updateCargos(batch);
                                return result;
                        case ADD_USER:
                                service= new UsersService();
                                result= service.add(batch);
                                return result;
                        case DELETE_USER:
                                service= new UsersService();
                                result= service.delete(batch);
                                return result;
                        case UPDATE_USER:
                                service= new UsersService();
                                result= service.update(batch);
                                return result;
                        case GET_USER:
                                service= new UsersService();
                                result= service.getEntity(batch);
                                return result;
                        case GET_USER_LIST:
                                service= new UsersService();
                                result= service.getList();
                                return result;
                        case AUTORIZATION_USER:
                                UsersService usersService= new UsersService();
                                result= usersService.autorization(batch);
                                return result;
                        case ADD_INVOICE:
                                service= new InvoiceService();
                                result= service.add(batch);
                                return result;
                        case DELETE_INVOICE:
                                service= new InvoiceService();
                                result= service.delete(batch);
                                return result;
                        case UPDATE_INVOICE:
                                service= new InvoiceService();
                                result= service.update(batch);
                                return result;
                        case GET_INVOICE:
                                service= new InvoiceService();
                                result= service.getEntity(batch);
                                return result;
                        case GET_INVOICE_LIST:
                                service= new InvoiceService();
                                result= service.getList();
                                return result;
                        case ADD_CARGO_IN_INVOICE:
                                result= invoiceService.addCargo(batch);
                                return result;
                        case GET_INVOICE_BY_USER_ID:
                                result= invoiceService.getInvoiceByUser(batch);
                                return result;
                        case ADD_STOCK:
                                service= new StockService();
                                result= service.add(batch);
                                return result;
                        case DELETE_STOCK:
                                service= new StockService();
                                result= service.delete(batch);
                                return result;
                        case DELETE_STOCKS:
                                StockService serviceStock= new StockService();
                                result= serviceStock.deleteStocks(batch);
                                return result;
                        case UPDATE_STOCK:
                                service= new StockService();
                                result= service.update(batch);
                                return result;
                        case GET_STOCK:
                                service= new StockService();
                                result= service.getEntity(batch);
                                return result;
                        case GET_STOCK_LIST:
                                service= new StockService();
                                result= service.getList();
                                return result;
                        default:  return null;
                }
        }

}
