package com.ftn.MyHousebackend.service;

import com.ftn.MyHousebackend.dto.DeviceDTO;
import com.ftn.MyHousebackend.dto.ObjectDTO;
import com.ftn.MyHousebackend.exception.ObjectNotFound;
import com.ftn.MyHousebackend.model.Device;
import com.ftn.MyHousebackend.model.Object;
import com.ftn.MyHousebackend.model.enums.DeviceType;
import com.ftn.MyHousebackend.repository.DeviceRepository;
import com.ftn.MyHousebackend.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ObjectRepository objectRepository;

    public List<DeviceDTO> getAllDevicesForObject(long objectId) {
        List<DeviceDTO> deviceDTOS = new ArrayList<>();
        deviceRepository.findAllByObject_Id(objectId).forEach(device -> {
            deviceDTOS.add(new DeviceDTO(device));
        });

        return deviceDTOS;
    }

    public DeviceDTO addDevice(DeviceDTO deviceDTO){

        Optional<Object> optionalObject = objectRepository.findById(deviceDTO.getObjectId());
        if(optionalObject.isEmpty()) throw new ObjectNotFound("Object not found!");

        Device newDevice = new Device(optionalObject.get(), deviceDTO.getName(), DeviceType.valueOf(deviceDTO.getType()));
        deviceRepository.saveAndFlush(newDevice);
        return new DeviceDTO(newDevice);
    }
}
