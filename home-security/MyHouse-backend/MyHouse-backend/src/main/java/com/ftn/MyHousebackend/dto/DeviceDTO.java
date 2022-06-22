package com.ftn.MyHousebackend.dto;

import com.ftn.MyHousebackend.model.Device;
import com.ftn.MyHousebackend.model.enums.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceDTO {
    private long id;
    private String name;
    private long objectId;

    private String type;

    public DeviceDTO() {
    }

    public DeviceDTO(long id, String name, long objectId, String type) {
        this.id = id;
        this.name = name;
        this.objectId = objectId;
        this.type = type;
    }

    public DeviceDTO(Device device) {
        this.id = device.getId();
        this.name = device.getName();
        this.objectId = device.getObject().getId();
        this.type = device.getType().name();
    }
}
