package com.bsep.device.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Alarm {

    private String messageType;
    private long objectId;
    private DeviceDTO device;
    private String message;
    private String date;
    private String time;

}
