package com.ftn.MyHousebackend.dto;

import com.ftn.MyHousebackend.model.User;

public class UserDTO {
    
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean deleted;
    private String role;


    public UserDTO() {
    }

    public UserDTO(long id, String username, String firstName, String lastName, Boolean deleted, String role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deleted = deleted;
        this.role = role;
    }

    public UserDTO(User user){
        this.deleted = user.isDeleted();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.id = user.getId();
        this.role = user.getRole();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean isDeleted() {
        return this.deleted;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public UserDTO id(long id) {
        setId(id);
        return this;
    }

    public UserDTO username(String username) {
        setUsername(username);
        return this;
    }

    public UserDTO firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public UserDTO lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public UserDTO deleted(Boolean deleted) {
        setDeleted(deleted);
        return this;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
