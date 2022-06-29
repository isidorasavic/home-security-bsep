package com.ftn.adminbackend.dto;

import com.ftn.adminbackend.model.Object;
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
    private UserDTO owner; //ako ne bude potrebe da se salje ceo user poslati samo username ili id
    private List<UserDTO> tenants;

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
