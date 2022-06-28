package com.ftn.adminbackend.model;

import com.ftn.adminbackend.model.enums.DeviceType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="devices")
@Getter
@Setter
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    private Object object;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    public Device() {
    }

    public Device(Object object, String name, DeviceType type) {
        this.object = object;
        this.name = name;
        this.type = type;
    }
}
