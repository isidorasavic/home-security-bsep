package com.ftn.MyHousebackend.dto;

import com.ftn.MyHousebackend.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
    private long id;
    private String username;
    private String role;
    private String password;
    private String firstName;
    private String lastName;
    private boolean deleted;

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public UserDTO() {
    }

    public UserDTO(long id, String username, String firstName, String lastName, boolean deleted, String role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deleted = deleted;
        this.role = role;
    }

    public UserDTO(User user){
        this.deleted = user.getDeleted();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.id = user.getId();
        this.role = user.getRole().name();
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }

}
