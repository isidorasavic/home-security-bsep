package com.ftn.MyHousebackend.model;

import com.ftn.MyHousebackend.model.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="object_messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObjectMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    private Object object;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name="message_type", nullable=false)
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column(name="message", nullable = false)
    private String message;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @Column(name="time", nullable = false)
    private LocalTime time;

}
