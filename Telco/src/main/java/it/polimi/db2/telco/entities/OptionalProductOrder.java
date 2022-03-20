package it.polimi.db2.telco.entities;

import javax.persistence.*;

@Entity
@Table(name = "optional_product_order")
public class OptionalProductOrder {
    @EmbeddedId
    OptionalProductOrderId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;

    @MapsId("optionalId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "optional_id", nullable = false)
    OptionalProduct optional;

    public OptionalProduct getOptional() {
        return optional;
    }

    public void setOptional(OptionalProduct optional) {
        this.optional = optional;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OptionalProductOrderId getId() {
        return id;
    }

    public void setId(OptionalProductOrderId id) {
        this.id = id;
    }
}