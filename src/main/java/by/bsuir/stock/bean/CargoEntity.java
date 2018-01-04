package by.bsuir.stock.bean;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "cargo", schema = "stock", catalog = "")
public class CargoEntity extends Shell {
    private int id;
    private String name;
    private double price;
    private double amount;
    private Date shelfLife;
    private Date receiptDate;
    private UsersEntity usersByIdUser;
    private InvoiceEntity invoiceByIdInvoise;
    private StockEntity stockByIdStock;
    private Collection<CargosininvoiceEntity> cargosininvoicesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "shelfLife", nullable = false)
    public Date getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }

    @Basic
    @Column(name = "receiptDate", nullable = true)
    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CargoEntity that = (CargoEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (shelfLife != null ? !shelfLife.equals(that.shelfLife) : that.shelfLife != null) return false;
        if (receiptDate != null ? !receiptDate.equals(that.receiptDate) : that.receiptDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (shelfLife != null ? shelfLife.hashCode() : 0);
        result = 31 * result + (receiptDate != null ? receiptDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "idInvoise", referencedColumnName = "id")
    public InvoiceEntity getInvoiceByIdInvoise() {
        return invoiceByIdInvoise;
    }

    public void setInvoiceByIdInvoise(InvoiceEntity invoiceByIdInvoise) {
        this.invoiceByIdInvoise = invoiceByIdInvoise;
    }

    @ManyToOne
    @JoinColumn(name = "idStock", referencedColumnName = "id")
    public StockEntity getStockByIdStock() {
        return stockByIdStock;
    }

    public void setStockByIdStock(StockEntity stockByIdStock) {
        this.stockByIdStock = stockByIdStock;
    }

    @OneToMany(mappedBy = "cargoByIdCargo",fetch = FetchType.EAGER)
    public Collection<CargosininvoiceEntity> getCargosininvoicesById() {
        return cargosininvoicesById;
    }

    public void setCargosininvoicesById(Collection<CargosininvoiceEntity> cargosininvoicesById) {
        this.cargosininvoicesById = cargosininvoicesById;
    }
}
