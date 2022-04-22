package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TotValueOptionalNoOptionalId implements Serializable {
    private static final long serialVersionUID = 7149375457940438157L;
    @Column(name = "package_id", nullable = false)
    private Integer packageId;
    @Column(name = "with_optional", nullable = false)
    private Integer withOptional;

    public Integer getWithOptional() {
        return withOptional;
    }

    public void setWithOptional(Integer withOptional) {
        this.withOptional = withOptional;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, withOptional);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotValueOptionalNoOptionalId entity = (TotValueOptionalNoOptionalId) o;
        return Objects.equals(this.packageId, entity.packageId) &&
                Objects.equals(this.withOptional, entity.withOptional);
    }
}