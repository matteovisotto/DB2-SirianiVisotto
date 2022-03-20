package it.polimi.db2.telco.entities;

import javax.persistence.*;

@Entity
@Table(name = "package_price")
public class PackagePrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id")
    ServicePackage _package;

    @Column(name = "validity_period", nullable = false)
    Integer validityPeriod;

    @Column(name = "price")
    Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Integer validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public ServicePackage get_package() {
        return _package;
    }

    public void set_package(ServicePackage _package) {
        this._package = _package;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}