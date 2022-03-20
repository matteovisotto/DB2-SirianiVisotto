package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "failed_payment")
public class FailedPayment {
    @Id
    @Column(name = "user_id", nullable = false)
    Integer id;

    @Column(name = "last_failure")
    Date lastFailure;

    @Column(name = "n_failures", nullable = false)
    Integer nFailures;

    public Integer getNFailures() {
        return nFailures;
    }

    public void setNFailures(Integer nFailures) {
        this.nFailures = nFailures;
    }

    public Date getLastFailure() {
        return lastFailure;
    }

    public void setLastFailure(Date lastFailure) {
        this.lastFailure = lastFailure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}