package it.polimi.db2.telco.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "suspended_orders")
public class SuspendedOrder {
    @EmbeddedId
    private SuspendedOrderId id;

    public SuspendedOrderId getId() {
        return id;
    }

    public void setId(SuspendedOrderId id) {
        this.id = id;
    }
}