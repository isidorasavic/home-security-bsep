package com.ftn.MyHousebackend.service;

import com.ftn.MyHousebackend.controller.AuthenticationController;
import com.ftn.MyHousebackend.dto.ObjectMessageDTO;
import com.ftn.MyHousebackend.exception.ObjectNotFound;
import com.ftn.MyHousebackend.model.Device;
import com.ftn.MyHousebackend.model.Object;
import com.ftn.MyHousebackend.model.ObjectMessage;
import com.ftn.MyHousebackend.model.enums.MessageType;
import com.ftn.MyHousebackend.repository.DeviceRepository;
import com.ftn.MyHousebackend.repository.ObjectMessageRepository;
import com.ftn.MyHousebackend.repository.ObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectMessageService {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectMessageService.class);


    @Autowired
    private ObjectMessageRepository objectMessageRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<ObjectMessageDTO> getAllMessagesForObject(long objectId) {
        if (objectRepository.findById(objectId).isEmpty()) throw new ObjectNotFound("Object not found!");

        List<ObjectMessageDTO> messages = new ArrayList<>();
        objectMessageRepository.findAllByObject_IdOrderByDate(objectId).forEach(message -> {
            messages.add(new ObjectMessageDTO(message));
        });
        return messages;
    }

    public List<ObjectMessageDTO> getAllMessagesForDevice(long deviceId) {
        List<ObjectMessageDTO> messages = new ArrayList<>();
        objectMessageRepository.findAllByDevice_IdOrderByDate(deviceId).forEach(message -> {
            messages.add(new ObjectMessageDTO(message));
        });
        return messages;
    }

    public ObjectMessageDTO addMessage(ObjectMessageDTO newMessage) {
        ObjectMessage message = new ObjectMessage();
        message.setMessage(newMessage.getMessage());
        message.setMessageType(MessageType.valueOf(newMessage.getMessageType()));
        message.setDate(LocalDate.parse(newMessage.getDate()));
        message.setTime(LocalTime.parse(newMessage.getTime()));

        Optional<Object> optObject = objectRepository.findById(newMessage.getObjectId());
        if (optObject.isEmpty()) throw new ObjectNotFound("Object not found!");

        Optional<Device> optDevice = deviceRepository.findById(newMessage.getDevice().getId());
        if (optDevice.isEmpty()) throw new ObjectNotFound("Device not found!");

        message.setObject(optObject.get());
        message.setDevice(optDevice.get());

        objectMessageRepository.saveAndFlush(message);

        return new ObjectMessageDTO(message);

    }




}
