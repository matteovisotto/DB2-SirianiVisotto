package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "avarage_purchase_optional_package")
public class AveragePurchaseOptionalPackage {
    @Id
    @Column(name = "package_id", nullable = false)
    private Integer id;

    @Column(name = "package_name", nullable = false)
    private String packageName;

    @Column(name = "avg_optional", nullable = false)
    private Double avgOptional;

    public Double getAvgOptional() {
        return avgOptional;
    }

    public void setAvgOptional(Double avgOptional) {
        this.avgOptional = avgOptional;
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