package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ServiceInPackageId implements Serializable {
    private static final long serialVersionUID = -245922949353686055L;
    @Column(name = "package_id", nullable = false)
    private Integer packageId;
    @Column(name = "service_id", nullable = false)
    private Integer serviceId;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, serviceId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceInPackageId entity = (ServiceInPackageId) o;
        return Objects.equals(this.packageId, entity.packageId) &&
                Objects.equals(this.serviceId, entity.serviceId);
    }
}