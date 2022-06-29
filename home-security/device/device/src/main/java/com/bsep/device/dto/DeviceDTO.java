package com.bsep.device.dto;

import com.bsep.device.model.Device;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceDTO {
    private long id;
    private String name;
    private long objectId;
    private String type;

    public DeviceDTO(Device device){
        this.id = device.getId();
        this.objectId = device.getObjectId();
        this.type = device.getDeviceType();
    }

}
