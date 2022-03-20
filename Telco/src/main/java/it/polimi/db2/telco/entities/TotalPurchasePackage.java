package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "total_purchase_package")
public class TotalPurchasePackage {
    @Id
    @Column(name = "package_id", nullable = false)
    Integer id;

    @Column(name = "package_name", nullable = false)
    String packageName;

    @Column(name = "tot_purchase", nullable = false)
    Integer totPurchase;

    public Integer getTotPurchase() {
        return totPurchase;
    }

    public void setTotPurchase(Integer totPurchase) {
        this.totPurchase = totPurchase;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}