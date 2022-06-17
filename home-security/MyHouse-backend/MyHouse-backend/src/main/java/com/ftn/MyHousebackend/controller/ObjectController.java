package com.ftn.MyHousebackend.controller;

import antlr.Token;
import com.ftn.MyHousebackend.dto.ObjectDTO;
import com.ftn.MyHousebackend.security.security.TokenUtils;
import com.ftn.MyHousebackend.service.ObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ObjectController {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectController.class);

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private ObjectService objectService;

    @ResponseBody
    // @PreAuthorize("hasRole('ADMIN')")
    //izmeniti da se username ni ne salje nego uzima na beku TODO
    @GetMapping(path = "/owner/{username}/objects")
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectDTO> getOwnerObjects(@PathVariable("username") String username) {
        return objectService.getOwnerObjects(username);
    }


}
