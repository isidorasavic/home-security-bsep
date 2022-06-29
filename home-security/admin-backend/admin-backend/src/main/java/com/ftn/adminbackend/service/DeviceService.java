package com.ftn.adminbackend.service;

import com.ftn.adminbackend.dto.DeviceDTO;
import com.ftn.adminbackend.exception.ObjectNotFound;
import com.ftn.adminbackend.model.Device;
import com.ftn.adminbackend.model.Object;
import com.ftn.adminbackend.model.enums.DeviceType;
import com.ftn.adminbackend.repository.DeviceRepository;
import com.ftn.adminbackend.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    private final ObjectRepository objectRepository;

    public DeviceService(DeviceRepository deviceRepository, ObjectRepository objectRepository) {
        this.deviceRepository = deviceRepository;
        this.objectRepository = objectRepository;
    }

    public List<DeviceDTO> getAllDevicesForObject(long objectId) {
        List<DeviceDTO> deviceDTOS = new ArrayList<>();
        deviceRepository.findAllByObject_Id(objectId).forEach(device -> {
            deviceDTOS.add(new DeviceDTO(device));
        });

        return deviceDTOS;
    }

    // TODO: SLATI NOVI DEVICE NA TRECI BEK VRV
    public DeviceDTO addDevice(DeviceDTO deviceDTO){

        Optional<Object> optionalObject = objectRepository.findById(deviceDTO.getObjectId());
        if(optionalObject.isEmpty()) throw new ObjectNotFound("Object not found!");

        Device newDevice = new Device(optionalObject.get(), deviceDTO.getName(), DeviceType.valueOf(deviceDTO.getType()));
        deviceRepository.saveAndFlush(newDevice);
        return new DeviceDTO(newDevice);
    }
}
