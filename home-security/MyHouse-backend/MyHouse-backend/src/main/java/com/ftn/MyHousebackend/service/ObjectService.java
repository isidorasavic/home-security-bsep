package com.ftn.MyHousebackend.service;

import com.ftn.MyHousebackend.controller.AuthenticationController;
import com.ftn.MyHousebackend.dto.ObjectDTO;
import com.ftn.MyHousebackend.model.Object;
import com.ftn.MyHousebackend.model.User;
import com.ftn.MyHousebackend.repository.ObjectRepository;
import com.ftn.MyHousebackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public List<ObjectDTO> getOwnerObjects(String username) {
        //provera da li postoji user sa username
        User user = userService.findByUsername(username);

        //TODO: testirati da li radi kad se dodaju neki tenantovi

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



}
