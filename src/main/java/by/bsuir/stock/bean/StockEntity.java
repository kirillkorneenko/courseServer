package by.bsuir.stock.bean;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "stock", schema = "stock", catalog = "")
public class StockEntity extends Shell {
    private int id;
    private double maxAmount;
    private Double congestion;
    private String location;
    private Collection<CargoEntity> cargosById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "maxAmount", nullable = false, precision = 0)
    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Basic
    @Column(name = "congestion", nullable = true, precision = 0)
    public Double getCongestion() {
        return congestion;
    }

    public void setCongestion(Double congestion) {
        this.congestion = congestion;
    }

    @Basic
    @Column(name = "location", nullable = false, length = 250)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockEntity that = (StockEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.maxAmount, maxAmount) != 0) return false;
        if (congestion != null ? !congestion.equals(that.congestion) : that.congestion != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(maxAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (congestion != null ? congestion.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "stockByIdStock",fetch = FetchType.EAGER)
    public Collection<CargoEntity> getCargosById() {
        return cargosById;
    }

    public void setCargosById(Collection<CargoEntity> cargosById) {
        this.cargosById = cargosById;
    }
}
