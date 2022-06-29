package com.ftn.adminbackend.controller;

import com.ftn.adminbackend.dto.CertificateGenDTO;
import com.ftn.adminbackend.dto.TextMessage;
import com.ftn.adminbackend.model.Certificate;
import com.ftn.adminbackend.model.SystemEntity;
import com.ftn.adminbackend.service.CertificateGenService;
import com.ftn.adminbackend.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("certificate_gen")
public class CertificateGenController {

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private CertificateGenService certificateGenService;

    /**
     * POST /server/create
     *
     * @return string which indicates status of operation creating certificate
     */
    @PostMapping(value = "/create", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TextMessage> create(@RequestBody CertificateGenDTO certificateGenDTO) throws Exception {
        loggerService.print("action = create certificate, status = success");
        TextMessage tm = certificateGenService.generateCertificate(certificateGenDTO);
        return new ResponseEntity<>(tm, HttpStatus.OK);
    }

    /**
     * GET /server/getCAs
     *
     * @return all CA certificates
     */
    @GetMapping(value = "/getCAs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> getCAs() throws Exception{
        loggerService.print("action = getCAs, status = success");
        List<Certificate> cas = certificateGenService.getAllCAs();
        return new ResponseEntity<>(cas, HttpStatus.OK);
    }

    /**
     * GET /server/getUIDs
     *
     * @return all extensions
     */
    @GetMapping(value = "/getUIDs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SystemEntity>> getUIDs() throws Exception{
        loggerService.print("action = getUIDs, status = success");
        List<SystemEntity> uids = certificateGenService.getAllUIDs();
        return new ResponseEntity<>(uids, HttpStatus.OK);
    }

}
