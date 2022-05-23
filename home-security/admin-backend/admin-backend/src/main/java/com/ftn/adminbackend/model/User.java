package com.ftn.adminbackend.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.adminbackend.dto.UserDTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Document("system_user")
public class User implements UserDetails{
    
    private static final long serialVersionUID = 1L;

    @Id
    protected long id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String role;
    private boolean deleted;

    protected User() {
    }
    
    public User(long id, String username, String password, String role, boolean deleted) {
    	this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.deleted = deleted;
    }

    public User(String username, String password, String role, boolean deleted) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.deleted = deleted;
    }

    public User(UserDTO userDTO){
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
        this.deleted = false;
    }


    public User(long id, String username, String password, String firstName, String lastName, String role, boolean deleted) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.deleted = deleted;
    }


    @Override
    public String toString() {
        return String.format(
                "User[username='%s' password='%s', role='%s']",
                username, password, role);
    }

    public long getId() {
        return this.id;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
    
    public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

    public boolean getDeleted() {
        return this.deleted;
    }
    public void setId(long id) {
        this.id = id;
    }


	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<Authority> authorities = new ArrayList<Authority>();
        
        if (role.equalsIgnoreCase("ADMIN")) {
            authorities.add(new Authority("ROLE_ADMIN"));
        }
        if (role.equalsIgnoreCase("OWNER")) {
            authorities.add(new Authority("ROLE_OWNER"));
        }
        if (role.equalsIgnoreCase("TENANT")) {
            authorities.add(new Authority("ROLE_TENANT"));
        }
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }



}
