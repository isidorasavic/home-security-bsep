package com.homesecurity.model;

import static javax.persistence.InheritanceType.JOINED;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.restaurant.model.Authority;
import com.homesecurity.model.UserRole;

@Entity
@Table(name = "system_user")
@Inheritance(strategy=JOINED)
public class User implements UserDetails{

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id", unique=true, nullable=false)
	    private Long id;

	    @Column(name = "username", unique=true, nullable=false)
	    private String username;

	    @Column(name = "password", unique=false, nullable=false)
	    private String password;

	    @Column(name = "deleted", unique=false, nullable=false)
	    private boolean deleted;

	    @ManyToOne
	    @JoinColumn(name = "role_id")
	    private UserRole role;

		public User() {
			super();
		}

		public User(String username, String password, boolean deleted, com.homesecurity.model.UserRole role) {
			super();
			this.username = username;
			this.password = password;
			this.deleted = deleted;
			this.role = role;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public boolean isDeleted() {
			return deleted;
		}

		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}

		public Long getId() {
			return id;
		}

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        ArrayList<Authority> authorities = new ArrayList<Authority>();
	        authorities.add(new Authority("ROLE_" + this.role.getName()));
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
