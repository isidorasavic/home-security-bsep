package com.ftn.adminbackend.service;

import com.ftn.adminbackend.dto.ObjectMessageDTO;
import com.ftn.adminbackend.repository.ObjectMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectMessageService {

    private final ObjectMessageRepository objectMessageRepository;

    public ObjectMessageService(ObjectMessageRepository objectMessageRepository) {
        this.objectMessageRepository = objectMessageRepository;
    }

    public List<ObjectMessageDTO> getAllMessagesForObject(long objectId) {
        // TODO: mozda provera da li postoji objekat?
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
