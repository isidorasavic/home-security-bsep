package com.ftn.adminbackend.controller;

import com.ftn.adminbackend.dto.UserDTO;
import com.ftn.adminbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @DeleteMapping(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO deleteUser(@PathVariable("id") long id) {
        return userService.deleteUser(id);
    }

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}/changeRole")
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

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/owners/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllOwners() {
        return userService.getAllOwners();
    }
}
