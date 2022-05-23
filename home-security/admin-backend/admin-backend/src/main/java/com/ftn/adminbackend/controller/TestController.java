package com.ftn.adminbackend.controller;

import org.springframework.stereotype.Controller;

import java.util.List;

import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.repository.UserRepository;

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
    
    @ResponseBody
    @GetMapping("/hello")
    public String getPatientsAppointments() {
        userRepository.deleteAll();
        userRepository.save(new User(1L, "admin1", "$2y$10$t4NZP3qGGdzGakospEzFHOPQngmjvi7dZeZSiwfiNz.1rv/smO0Ce", "ADMIN", false));
		userRepository.save(new User(2L, "admin2", "test", "ADMIN", false));
        List<User> allUsers = userRepository.findAll();
        String retVal = "hello :) \n";
        for(User user : allUsers){
            retVal += user.toString()+"\n";
        }
        return  retVal;

        
    }
}
