package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tot_value_optional_no_optional")
public class TotValueOptionalNoOptional {
    @EmbeddedId
    private TotValueOptionalNoOptionalId id;

    @Column(name = "package_name", nullable = false)
    private String packageName;

    @Column(name = "tot_value")
    private Double totValue;

    public Double getTotValue() {
        return totValue;
    }

    public void setTotValue(Double totValue) {
        this.totValue = totValue;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public TotValueOptionalNoOptionalId getId() {
        return id;
    }

    public void setId(TotValueOptionalNoOptionalId id) {
        this.id = id;
    }
}