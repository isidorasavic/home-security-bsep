package com.ftn.MyHousebackend.service;

import com.ftn.MyHousebackend.dto.ObjectMessageDTO;
import com.ftn.MyHousebackend.exception.ObjectNotFound;
import com.ftn.MyHousebackend.model.Object;
import com.ftn.MyHousebackend.repository.ObjectMessageRepository;
import com.ftn.MyHousebackend.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectMessageService {

    @Autowired
    private ObjectMessageRepository objectMessageRepository;

    @Autowired
    private ObjectRepository objectRepository;

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




}
