package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "total_purchase_package_validity")
public class TotalPurchasePackageValidity {
    @EmbeddedId
    private TotalPurchasePackageValidityId id;

    @Column(name = "package_name", nullable = false)
    private String packageName;

    @Column(name = "tot_purchase", nullable = false)
    private Integer totPurchase;

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

    public TotalPurchasePackageValidityId getId() {
        return id;
    }

    public void setId(TotalPurchasePackageValidityId id) {
        this.id = id;
    }
}