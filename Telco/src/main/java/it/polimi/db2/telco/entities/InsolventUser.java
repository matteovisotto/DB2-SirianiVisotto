package it.polimi.db2.telco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "insolvent_user")
public class InsolventUser {
    @Id
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
}