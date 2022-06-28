package com.ftn.adminbackend.controller;

import com.ftn.adminbackend.dto.DeviceDTO;
import com.ftn.adminbackend.dto.ObjectMessageDTO;
import com.ftn.adminbackend.service.DeviceService;
import com.ftn.adminbackend.service.ObjectMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceController {

    private final ObjectMessageService objectMessageService;

    private final DeviceService deviceService;

    public DeviceController(ObjectMessageService objectMessageService, DeviceService deviceService) {
        this.objectMessageService = objectMessageService;
        this.deviceService = deviceService;
    }

    @ResponseBody
    @GetMapping(path = "/device/{deviceId}/messages")
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectMessageDTO> getDeviceMessages(@PathVariable("deviceId") long deviceId) {
        return objectMessageService.getAllMessagesForDevice(deviceId);
    }

    @ResponseBody
    @GetMapping(path = "/object/{objectId}/devices")
    @ResponseStatus(HttpStatus.OK)
    //TODO
    public List<DeviceDTO> getObjectDevices(@PathVariable("objectId") long objectId) {
        return deviceService.getAllDevicesForObject(objectId);
    }

    @ResponseBody
    @PostMapping(path = "/device")
    @ResponseStatus(HttpStatus.OK)
    //TODO
    public DeviceDTO addDevice(@RequestBody DeviceDTO deviceDTO) {
        return deviceService.addDevice(deviceDTO);
    }
}
