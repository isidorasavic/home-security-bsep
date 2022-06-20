package com.ftn.MyHousebackend.dto;

import com.ftn.MyHousebackend.model.Device;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceDTO {
    private long id;
    private String name;
    private long objectId;

    public DeviceDTO() {
    }

    public DeviceDTO(long id, String name, long objectId) {
        this.id = id;
        this.name = name;
        this.objectId = objectId;
    }

    public DeviceDTO(Device device) {
        this.id = device.getId();
        this.name = device.getName();
        this.objectId = device.getObject().getId();
    }
}
