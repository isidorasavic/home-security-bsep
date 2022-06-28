package com.ftn.adminbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.adminbackend.model.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User implements UserDetails{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "username", nullable = false)
    protected String username;

    @Column(name = "password", nullable = false, length = 60)
    protected String password;

    @Column(name="role", nullable = false)
    @Enumerated(EnumType.STRING)
    protected UserRole role;

    @Column(name = "deleted", nullable = false)
    protected Boolean deleted;

    @Column(name = "blocked", nullable = false)
    protected Boolean blocked;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "tenant_objects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "object_id"))
    private List<Object> tenantObjects = new ArrayList<>();


    public User() {
        this.tenantObjects = new ArrayList<>();
    }

    public User(long id, String firstName, String lastName, String username, String password, UserRole role, Boolean deleted, Boolean blocked) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.deleted = deleted;
        this.tenantObjects = new ArrayList<>();
        this.blocked = blocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<Authority> authorities = new ArrayList<>();
        if (role == UserRole.ADMIN) {
            authorities.add(new Authority("ROLE_ADMIN"));
        }
        if (role == UserRole.OWNER) {
            authorities.add(new Authority("ROLE_OWNER"));
        }
        if (role == UserRole.TENANT) {
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

    public void addObject(Object object) {
        this.tenantObjects.add(object);
    }
}
