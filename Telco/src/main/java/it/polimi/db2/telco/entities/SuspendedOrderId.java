package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SuspendedOrderId implements Serializable {
    private static final long serialVersionUID = -6558232249729298768L;
    @Column(name = "order_id", nullable = false)
    private Integer orderId;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuspendedOrderId entity = (SuspendedOrderId) o;
        return Objects.equals(this.orderId, entity.orderId) &&
                Objects.equals(this.userId, entity.userId);
    }
}