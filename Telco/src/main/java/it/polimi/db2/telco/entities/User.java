package it.polimi.db2.telco.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "name", nullable = false, length = 225)
    String name;

    @Column(name = "surname", nullable = false, length = 225)
    String surname;

    @Column(name = "username", nullable = false, length = 225)
    String username;

    @Column(name = "email", nullable = false, length = 225)
    String email;

    @Column(name = "password", nullable = false, length = 225)
    String password;

    @Column(name = "insolvent", nullable = false)
    Integer insolvent;

    @OneToMany(mappedBy = "user")
    List<PaymentHistory> paymentHistories = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Alert> alerts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public List<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }

    public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
        this.paymentHistories = paymentHistories;
    }

    public Integer getInsolvent() {
        return insolvent;
    }

    public void setInsolvent(Integer insolvent) {
        this.insolvent = insolvent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}