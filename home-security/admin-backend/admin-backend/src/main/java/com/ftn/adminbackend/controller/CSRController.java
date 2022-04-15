package com.ftn.adminbackend.controller;

import com.ftn.adminbackend.dto.CSRDTO;
import com.ftn.adminbackend.service.CSRService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping(value="/api/csr", produces=MediaType.APPLICATION_JSON_VALUE)
public class CSRController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);


    @Autowired 
    private CSRService csrService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/create")
	public String createAuthenticationToken(@RequestBody CSRDTO csrDTO){
        LOG.info("Recieved request for creating csr");
        String retVal =  csrService.generateCSR(csrDTO);
        LOG.info(retVal);
        return retVal;
    }


    
}
