package com.ftn.MyHousebackend.controller;

import com.ftn.MyHousebackend.dto.ObjectMessageDTO;
import com.ftn.MyHousebackend.dto.UserDTO;
import com.ftn.MyHousebackend.service.ObjectMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    @Autowired
    private ObjectMessageService objectMessageService;

    @ResponseBody
    @PostMapping(path = "/addMessage")
    public void addMessage(@RequestBody ObjectMessageDTO messageDTO) {
        objectMessageService.addMessage(messageDTO);
    }
}
