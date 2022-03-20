package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "total_purchase_optional")
public class TotalPurchaseOptional {
    @Id
    @Column(name = "optional_id", nullable = false)
    Integer id;

    @Column(name = "optional_name", nullable = false)
    String optionalName;

    @Column(name = "tot_purchase", nullable = false)
    Integer totPurchase;

    public Integer getTotPurchase() {
        return totPurchase;
    }

    public void setTotPurchase(Integer totPurchase) {
        this.totPurchase = totPurchase;
    }

    public String getOptionalName() {
        return optionalName;
    }

    public void setOptionalName(String optionalName) {
        this.optionalName = optionalName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}