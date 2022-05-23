package com.ftn.MyHousebackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.ftn.MyHousebackend.dto.UserDTO;
import com.ftn.MyHousebackend.model.User;
import com.ftn.MyHousebackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/search/{searchWord}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> searchUsers(@PathVariable("searchWord") String searchWord) {
        return userService.searchUsers(searchWord);
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO deleteUser(@PathVariable("username") String username) {
        return userService.deleteUser(username);
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/changeRole")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO changeRole(@RequestParam("username") String username, @RequestParam("newRole") String newRole) {
        return userService.changeRole(username, newRole);
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/addUser")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }
}
