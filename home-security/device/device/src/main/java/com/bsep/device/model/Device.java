package com.bsep.device.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Document("devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Device {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "object_id", nullable = false)
    private Long objectId;

    @Column(name="device_type", nullable = false)
    private String  deviceType;

}
