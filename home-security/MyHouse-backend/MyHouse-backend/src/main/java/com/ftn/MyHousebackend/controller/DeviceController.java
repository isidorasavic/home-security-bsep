package com.ftn.MyHousebackend.controller;

import com.ftn.MyHousebackend.dto.DeviceDTO;
import com.ftn.MyHousebackend.dto.ObjectMessageDTO;
import com.ftn.MyHousebackend.model.ObjectMessage;
import com.ftn.MyHousebackend.service.DeviceService;
import com.ftn.MyHousebackend.service.ObjectMessageService;
import com.ftn.MyHousebackend.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceController {

    @Autowired
    private ObjectMessageService objectMessageService;

    @Autowired
    private DeviceService deviceService;

    @ResponseBody
    @GetMapping(path = "/device/{deviceId}/messages")
    @ResponseStatus(HttpStatus.OK)
    //TODO: sve role
    public List<ObjectMessageDTO> getDeviceMessages(@PathVariable("deviceId") long deviceId) {
        return objectMessageService.getAllMessagesForDevice(deviceId);
    }

    @ResponseBody
    @GetMapping(path = "/object/{objectId}/devices")
    @ResponseStatus(HttpStatus.OK)
    //TODO sve role
    public List<DeviceDTO> getObjectDevices(@PathVariable("objectId") long objectId) {
        return deviceService.getAllDevicesForObject(objectId);
    }

    @ResponseBody
    @PostMapping(path = "/device")
    @ResponseStatus(HttpStatus.OK)
    //TODO sve role
    public DeviceDTO addDevice(@RequestBody DeviceDTO deviceDTO) {
        return deviceService.addDevice(deviceDTO);
    }
}
