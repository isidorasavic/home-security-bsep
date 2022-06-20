package com.ftn.MyHousebackend.service;

import com.ftn.MyHousebackend.dto.DeviceDTO;
import com.ftn.MyHousebackend.dto.ObjectDTO;
import com.ftn.MyHousebackend.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<DeviceDTO> getAllDevicesForObject(long objectId) {
        List<DeviceDTO> deviceDTOS = new ArrayList<>();
        deviceRepository.findAllByObject_Id(objectId).forEach(device -> {
            deviceDTOS.add(new DeviceDTO(device));
        });

        return deviceDTOS;
    }
}
