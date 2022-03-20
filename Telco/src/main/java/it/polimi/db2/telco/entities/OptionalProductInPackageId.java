package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OptionalProductInPackageId implements Serializable {
    static final long serialVersionUID = 5411829317774186134L;

    @Column(name = "package_id", nullable = false)
    Integer packageId;

    @Column(name = "optional_product_id", nullable = false)
    Integer optionalProductId;

    public Integer getOptionalProductId() {
        return optionalProductId;
    }

    public void setOptionalProductId(Integer optionalProductId) {
        this.optionalProductId = optionalProductId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, optionalProductId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalProductInPackageId entity = (OptionalProductInPackageId) o;
        return Objects.equals(this.packageId, entity.packageId) &&
                Objects.equals(this.optionalProductId, entity.optionalProductId);
    }
}