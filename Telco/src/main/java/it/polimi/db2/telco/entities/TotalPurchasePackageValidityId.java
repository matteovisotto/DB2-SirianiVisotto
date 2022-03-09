package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TotalPurchasePackageValidityId implements Serializable {
    private static final long serialVersionUID = -8770311835360405013L;

    @Column(name = "package_id", nullable = false)
    private Integer packageId;

    @Column(name = "validity_period", nullable = false)
    private Integer validityPeriod;

    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Integer validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(validityPeriod, packageId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalPurchasePackageValidityId entity = (TotalPurchasePackageValidityId) o;
        return Objects.equals(this.validityPeriod, entity.validityPeriod) &&
                Objects.equals(this.packageId, entity.packageId);
    }
}