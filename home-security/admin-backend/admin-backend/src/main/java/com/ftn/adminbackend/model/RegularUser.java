package com.ftn.adminbackend.model;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document("regular_user")
public class RegularUser extends User{

    private String name;
    private String surname;


    public RegularUser() {
    }

    public RegularUser(String username, String password, String name, String surname) {
        super(username, password, "USER", false);
        this.name = name;
        this.surname = surname;
    }

    public RegularUser(long id, String username, String password, String name, String surname) {
        super(id, username, password, "USER", false);
        this.name = name;
        this.surname = surname;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    
}
