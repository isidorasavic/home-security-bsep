package com.ftn.MyHousebackend.service;

import com.ftn.MyHousebackend.dto.ObjectDTO;
import com.ftn.MyHousebackend.model.Object;
import com.ftn.MyHousebackend.model.User;
import com.ftn.MyHousebackend.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private UserService userService;

    public List<ObjectDTO> getOwnerObjects(String username) {
        //provera da li postoji user sa userId
        User user = userService.findByUsername(username);

        List<ObjectDTO> objectsList = new ArrayList<>();
        objectRepository.findAllByOwnerId(user.getId()).forEach(object -> {
            objectsList.add(new ObjectDTO(object));
        });

        return objectsList;
    }



}
