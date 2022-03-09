package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OptionalProductOrderId implements Serializable {
    private static final long serialVersionUID = -4194683477023128607L;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "optional_id", nullable = false)
    private Integer optionalId;

    public Integer getOptionalId() {
        return optionalId;
    }

    public void setOptionalId(Integer optionalId) {
        this.optionalId = optionalId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, optionalId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalProductOrderId entity = (OptionalProductOrderId) o;
        return Objects.equals(this.orderId, entity.orderId) &&
                Objects.equals(this.optionalId, entity.optionalId);
    }
}