package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "total_purchase_package_optional")
public class TotalPurchasePackageOptional {
    @EmbeddedId
    private TotalPurchasePackageOptionalId id;

    @Column(name = "tot_purchase", nullable = false)
    private Integer totPurchase;

    @Column(name = "package_name", nullable = false)
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getTotPurchase() {
        return totPurchase;
    }

    public void setTotPurchase(Integer totPurchase) {
        this.totPurchase = totPurchase;
    }

    public TotalPurchasePackageOptionalId getId() {
        return id;
    }

    public void setId(TotalPurchasePackageOptionalId id) {
        this.id = id;
    }
}