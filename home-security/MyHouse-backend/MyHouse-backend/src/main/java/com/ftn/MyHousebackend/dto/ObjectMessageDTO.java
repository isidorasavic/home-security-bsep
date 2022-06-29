package com.ftn.MyHousebackend.dto;

import com.ftn.MyHousebackend.model.ObjectMessage;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class ObjectMessageDTO {

    private String messageType;
    private long objectId;

    private DeviceDTO device;
    private String message;
    private String date;
    private String time;

    public ObjectMessageDTO(ObjectMessage objectMessage) {
        this.messageType = objectMessage.getMessageType().name();
        this.device = new DeviceDTO(objectMessage.getDevice());
        this.message = objectMessage.getMessage();
        this.objectId = objectMessage.getObject().getId();
        this.date = objectMessage.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.time = objectMessage.getTime().format(DateTimeFormatter.ofPattern("mm:HH"));
    }

    public ObjectMessageDTO() {
    }

    public ObjectMessageDTO(String messageType, long objectId, DeviceDTO device, String message, String date, String time) {
        this.messageType = messageType;
        this.objectId = objectId;
        this.device = device;
        this.message = message;
        this.date = date;
        this.time = time;
    }
}
