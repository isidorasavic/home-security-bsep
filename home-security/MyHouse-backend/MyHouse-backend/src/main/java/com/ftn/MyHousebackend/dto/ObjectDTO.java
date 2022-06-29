package com.ftn.MyHousebackend.dto;

import com.ftn.MyHousebackend.model.Object;
import com.ftn.MyHousebackend.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ObjectDTO {

    private long id;
    private String name;
    private String type;
    private UserDTO owner;
    private List<UserDTO> tenants;

    public ObjectDTO() {
    }

    public ObjectDTO(long id, String name, String type, UserDTO owner, List<UserDTO> tenants) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.owner = owner;
        this.tenants = tenants;
    }

    public ObjectDTO(Object object) {
        this.id = object.getId();
        this.name = object.getName();
        this.type = object.getObjectType().name();
        this.owner = new UserDTO(object.getOwner());
        tenants = new ArrayList<>();
        object.getTenants().forEach(tenant -> {
            this.tenants.add(new UserDTO(tenant));
        });
    }

}
