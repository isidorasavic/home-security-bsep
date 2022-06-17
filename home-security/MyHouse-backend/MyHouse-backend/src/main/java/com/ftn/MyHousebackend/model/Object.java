package com.ftn.MyHousebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.MyHousebackend.model.enums.ObjectType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="objects")
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ObjectType objectType;

    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne
    private User owner;

    @ManyToMany(mappedBy = "tenantObjects", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> tenants;

}
