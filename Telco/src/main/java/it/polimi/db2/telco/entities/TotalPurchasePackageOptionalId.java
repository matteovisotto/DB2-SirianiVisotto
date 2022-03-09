package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TotalPurchasePackageOptionalId implements Serializable {
    private static final long serialVersionUID = -4633240450405660716L;

    @Column(name = "package_id", nullable = false)
    private Integer packageId;

    @Column(name = "has_optional_product", nullable = false)
    private Integer hasOptionalProduct;

    public Integer getHasOptionalProduct() {
        return hasOptionalProduct;
    }

    public void setHasOptionalProduct(Integer hasOptionalProduct) {
        this.hasOptionalProduct = hasOptionalProduct;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, hasOptionalProduct);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalPurchasePackageOptionalId entity = (TotalPurchasePackageOptionalId) o;
        return Objects.equals(this.packageId, entity.packageId) &&
                Objects.equals(this.hasOptionalProduct, entity.hasOptionalProduct);
    }
}