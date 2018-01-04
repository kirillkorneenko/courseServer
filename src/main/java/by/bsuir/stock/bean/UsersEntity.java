package by.bsuir.stock.bean;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "stock", catalog = "")
public class UsersEntity extends Shell {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surName;
    private String status;
    private Collection<CargoEntity> cargosById;
    private Collection<InvoiceEntity> invoicesById;
    private Collection<InvoiceEntity> invoicesById_0;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 50)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surName", nullable = true, length = 50)
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surName != null ? !surName.equals(that.surName) : that.surName != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<CargoEntity> getCargosById() {
        return cargosById;
    }

    public void setCargosById(Collection<CargoEntity> cargosById) {
        this.cargosById = cargosById;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<InvoiceEntity> getInvoicesById() {
        return invoicesById;
    }

    public void setInvoicesById(Collection<InvoiceEntity> invoicesById) {
        this.invoicesById = invoicesById;
    }

    @OneToMany(mappedBy = "usersByIdEmployee")
    public Collection<InvoiceEntity> getInvoicesById_0() {
        return invoicesById_0;
    }

    public void setInvoicesById_0(Collection<InvoiceEntity> invoicesById_0) {
        this.invoicesById_0 = invoicesById_0;
    }
}
