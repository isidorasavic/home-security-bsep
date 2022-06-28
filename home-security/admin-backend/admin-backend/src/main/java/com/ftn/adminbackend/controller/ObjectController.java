package com.ftn.adminbackend.controller;

import com.ftn.adminbackend.dto.ObjectDTO;
import com.ftn.adminbackend.dto.ObjectMessageDTO;
import com.ftn.adminbackend.dto.UserDTO;
import com.ftn.adminbackend.security.TokenUtils;
import com.ftn.adminbackend.service.ObjectMessageService;
import com.ftn.adminbackend.service.ObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ObjectController {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectController.class);

    private final TokenUtils tokenUtils;

    private final ObjectService objectService;

    private final ObjectMessageService objectMessageService;

    public ObjectController(TokenUtils tokenUtils, ObjectService objectService, ObjectMessageService objectMessageService) {
        this.tokenUtils = tokenUtils;
        this.objectService = objectService;
        this.objectMessageService = objectMessageService;
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    //izmeniti da se username ni ne salje nego uzima na beku TODO
    @GetMapping(path = "/user/{username}/objects")
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectDTO> getOwnerObjects(@PathVariable("username") String username) {
        return objectService.getOwnerObjects(username);
    }

    @ResponseBody
    @GetMapping(path = "/object/{objectId}/messages")
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectMessageDTO> getObjectMessages(@PathVariable("objectId") long objectId) {
        return objectMessageService.getAllMessagesForObject(objectId);
    }

    @ResponseBody
    @PostMapping(path = "/object/tenant")
    public UserDTO addTenantToObject(@RequestParam("objectId") long objectId, @RequestParam("tenant") String tenant) {
        return objectService.addTennatToObject(objectId, tenant);
    }

    @ResponseBody
    @GetMapping(path = "/object/tenantOptions")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getPotentialTenants(@RequestParam("objectId") long objectId) {
        return objectService.getPotentialTenants(objectId);
    }

//    @ResponseBody
//    @GetMapping(path = "/all/{username}/objects")
//    @ResponseStatus(HttpStatus.OK)
//    public List<UserDTO> getPotentialTenants(@RequestParam("objectId") long objectId) {
//        return objectService.getPotentialTenants(objectId);
//    }
}
