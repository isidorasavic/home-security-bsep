package com.ftn.MyHousebackend.controller;

import antlr.Token;
import com.ftn.MyHousebackend.dto.ObjectDTO;
import com.ftn.MyHousebackend.dto.ObjectMessageDTO;
import com.ftn.MyHousebackend.dto.ReportDTO;
import com.ftn.MyHousebackend.dto.UserDTO;
import com.ftn.MyHousebackend.model.ObjectMessage;
import com.ftn.MyHousebackend.security.security.TokenUtils;
import com.ftn.MyHousebackend.service.ObjectMessageService;
import com.ftn.MyHousebackend.service.ObjectService;
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

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private ObjectService objectService;

    @Autowired
    private ObjectMessageService objectMessageService;

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
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

    @ResponseBody
    @GetMapping(path = "/object/{id}/report/from/{dateFrom}/to/{dateTo}")
    @ResponseStatus(HttpStatus.OK)
    public ReportDTO getReport(@PathVariable("id") long id, @PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo) {
        return objectService.getReport(id, dateFrom, dateTo);
    }

    @ResponseBody
    @GetMapping(path = "/allObjects")
    //admin
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectDTO> getAllObjects() {
        return objectService.getAllObjects();
    }

    @ResponseBody
    @PutMapping(path = "/changeObject/{object_id}/owner")
    //admin
    @ResponseStatus(HttpStatus.OK)
    public ObjectDTO changeOwner(@PathVariable("object_id") long object_id, @RequestParam("user_id") long user_id) {
        return objectService.changeObjectOwner(object_id, user_id);
    }

    @ResponseBody
    @PostMapping(path = "/addObject")
    //admin
    @ResponseStatus(HttpStatus.OK)
    public ObjectDTO addObject(@RequestBody ObjectDTO objectDTO) {
        return objectService.addObject(objectDTO);
    }

}
