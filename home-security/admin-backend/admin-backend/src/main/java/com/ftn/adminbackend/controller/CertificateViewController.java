package com.ftn.adminbackend.controller;

import com.ftn.adminbackend.dto.CertificateDetailsDTO;
import com.ftn.adminbackend.dto.CertificateViewDTO;
import com.ftn.adminbackend.dto.TextMessage;
import com.ftn.adminbackend.dto.getCertificatesDTO;
import com.ftn.adminbackend.service.CertificateReaderService;
import com.ftn.adminbackend.service.CertificateViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value ="/api/certificate")
public class CertificateViewController {

    @Autowired
    private CertificateReaderService certificateReaderService;

    @Autowired
    private CertificateViewService certificateViewService;

    @GetMapping(value = "/test")
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping(value = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificateViewDTO>> getCertificates(@RequestBody getCertificatesDTO getCertificatesDTO) throws Exception {
        return new ResponseEntity<>(certificateViewService.convertCertificatesToDTOList(certificateViewService.getCertificates(getCertificatesDTO.getCertType(), getCertificatesDTO.getPassword())), HttpStatus.OK);
    }

    @GetMapping(value = "/certificateDetails")
    public ResponseEntity<CertificateDetailsDTO> getCertificateDetails(@RequestParam(value = "serialNumber", required = true) String serialNumber) throws Exception {

        return new ResponseEntity<>(certificateViewService.getDetails(serialNumber), HttpStatus.OK);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<TextMessage> download(@RequestParam(value = "serialNumber", required = true) String serialNumber) throws Exception {
        TextMessage tm = new TextMessage();
        tm.setArrayBuffer(certificateViewService.download(serialNumber));
        return new ResponseEntity<>(tm, HttpStatus.OK);
    }

}
