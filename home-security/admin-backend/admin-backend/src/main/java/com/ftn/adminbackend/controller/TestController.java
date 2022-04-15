package com.ftn.adminbackend.controller;

import org.springframework.stereotype.Controller;

import java.util.List;

import com.ftn.adminbackend.dto.CSRDTO;
import com.ftn.adminbackend.model.RegularUser;
import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.repository.RegularUserRepository;
import com.ftn.adminbackend.repository.UserRepository;
import com.ftn.adminbackend.service.CSRService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/api/test", produces=MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegularUserRepository regularUserRepository;

    @Autowired
    private CSRService csrService;
    
    @ResponseBody
    @GetMapping("/hello")
    public String getPatientsAppointments() {
        userRepository.deleteAll();
        userRepository.save(new User(1L, "admin1", "$2y$10$t4NZP3qGGdzGakospEzFHOPQngmjvi7dZeZSiwfiNz.1rv/smO0Ce", "ADMIN", false));
        regularUserRepository.deleteAll();
		regularUserRepository.save(new RegularUser(10L, "mikamika", "pas123", "mika", "mikic"));

        List<User> allUsers = userRepository.findAll();
        String retVal = "hello :) \n";
        for(User user : allUsers){
            retVal += user.toString()+"\n";
        }
        return  retVal;

        
    }
}
