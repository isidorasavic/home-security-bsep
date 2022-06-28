package com.ftn.adminbackend.service;

import com.ftn.adminbackend.controller.AuthenticationController;
import com.ftn.adminbackend.dto.ObjectDTO;
import com.ftn.adminbackend.dto.UserDTO;
import com.ftn.adminbackend.exception.InvalidArgumentException;
import com.ftn.adminbackend.exception.ObjectNotFound;
import com.ftn.adminbackend.exception.UserNotFoundException;
import com.ftn.adminbackend.model.Object;
import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.model.enums.UserRole;
import com.ftn.adminbackend.repository.ObjectRepository;
import com.ftn.adminbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    private final ObjectRepository objectRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    public ObjectService(ObjectRepository objectRepository, UserRepository userRepository, UserService userService) {
        this.objectRepository = objectRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<ObjectDTO> getOwnerObjects(String username) {
        //provera da li postoji user sa username
        User user = userService.findByUsername(username);

        List<ObjectDTO> objectsList = new ArrayList<>();
        objectRepository.findAllByOwnerId(user.getId()).forEach(object -> {
            objectsList.add(new ObjectDTO(object));
            LOG.info(object.getName());
        });
        objectRepository.findAllByOwnerIdNot(user.getId()).forEach(object -> {
            if(object.getTenants().contains(user)){
                objectsList.add(new ObjectDTO(object));
                LOG.info(object.getName());
            }
        });

        return objectsList;
    }

    public UserDTO addTennatToObject(long objectId, String tenantUsername) {
        Optional<Object> optObject = objectRepository.findById(objectId);
        if (optObject.isEmpty()) throw new ObjectNotFound("Object not found!");

        Optional<User> optUser = userRepository.findByUsername(tenantUsername);
        if(optUser.isEmpty()) throw new UserNotFoundException("User not found!");

        Object object = optObject.get();
        User user = optUser.get();

        if (object.getOwner().getUsername().equals(user.getUsername())) throw new InvalidArgumentException("Object owner can't be tenant!");

        if(user.getRole() == UserRole.ADMIN)  throw new InvalidArgumentException("Admin can't be tenant!");

        if(object.getTenants().contains(user)) throw new InvalidArgumentException("User is already tenant!");

        user.addObject(object);
        userRepository.saveAndFlush(user);

        object.addTenant(user);
        objectRepository.saveAndFlush(object);

        return new UserDTO(user);
    }

    public List<UserDTO> getPotentialTenants(long objectId) {

        Optional<Object> optObject = objectRepository.findById(objectId);
        if (optObject.isEmpty()) throw new ObjectNotFound("Object not found!");

        Object object = optObject.get();

        List<User> potentialTenants = userRepository.findByRoleNotAndDeletedIsFalse(UserRole.ADMIN);

        List<UserDTO> userDTOS = new ArrayList<>();
        potentialTenants.forEach(user -> {
            if (!object.getTenants().contains(user) && object.getOwner()!=user){
                userDTOS.add(new UserDTO(user));
            }
        });

        return userDTOS;
    }



}
