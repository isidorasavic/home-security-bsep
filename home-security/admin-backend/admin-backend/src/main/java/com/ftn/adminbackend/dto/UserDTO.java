package com.ftn.adminbackend.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.ftn.adminbackend.model.User;

import java.util.Objects;

@XmlRootElement
public class UserDTO {

    private String username;
    private String role;
    private String password;
    private String firstName;
    private String lastName;

    public UserDTO(String username, String role, String password, String firstName, String lastName) {
        this.username = username;
        this.role = role;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(username, userDTO.username) && Objects.equals(role, userDTO.role) && Objects.equals(password, userDTO.password) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role, password, firstName, lastName);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
