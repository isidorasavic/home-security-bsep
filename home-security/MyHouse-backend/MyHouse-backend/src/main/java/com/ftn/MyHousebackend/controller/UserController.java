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
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/user/search/{searchWord}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> searchUsers(@PathVariable("searchWord") String searchWord) {
        return userService.searchUsers(searchWord);
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/users/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAllUsers() {
        return userService.findAll();
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/user/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO deleteUser(@PathVariable("id") long id) {
        return userService.deleteUser(id);
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/user/{id}/changeRole")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO changeRole(@PathVariable("id") long id, @RequestParam("newRole") String newRole) {
        return userService.changeRole(id, newRole);
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/addUser")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @ResponseBody
    // preauthorize admin
    @PutMapping(path = "/blockUnblock/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO blockUnblockUser(@PathVariable("id") long id) {
        return userService.blockUnblockUser(id);
    }
}
