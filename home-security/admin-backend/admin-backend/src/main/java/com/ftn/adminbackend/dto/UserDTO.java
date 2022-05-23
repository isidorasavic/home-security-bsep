package com.ftn.adminbackend.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.ftn.adminbackend.model.User;

@XmlRootElement
public class UserDTO {

    private String username;
    private String role;
    private String password;
    private String firstName;
    private String lastName;


    public UserDTO() {
    }



    public UserDTO(String username, String role, String password, String firstname, String lastName) {
        this.username = username;
        this.role = role;
        this.password = password;
        this.firstName = firstname;
        this.lastName = lastName;
    }
    


    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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


    public String getFirstname() {
        return this.firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
}
