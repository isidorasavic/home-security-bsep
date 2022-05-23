package com.ftn.adminbackend.controller;

import com.ftn.adminbackend.dto.UserDTO;
import com.ftn.adminbackend.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    @ResponseBody
    public UserDTO addUser(@RequestBody UserDTO newUser) throws Exception{
        LOG.info("Recieved request to add new user: "+newUser.getUsername());
        return userService.addUser(newUser);

    }

    
}
