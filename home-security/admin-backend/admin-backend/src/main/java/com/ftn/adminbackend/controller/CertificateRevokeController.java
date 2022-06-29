package com.ftn.adminbackend.controller;

import com.ftn.adminbackend.dto.TextMessage;
import com.ftn.adminbackend.model.enums.RevokeReason;
import com.ftn.adminbackend.service.CertificateRevokeService;
import com.ftn.adminbackend.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("certificate_revoke")
public class CertificateRevokeController {
    @Autowired
    private CertificateRevokeService certificateRevokeService;

    @Autowired
    private LoggerService loggerService;

    /**
     * POST /server/certificate_revoke/revoke
     *
     * @return string which indicates status of operation revoking certificate
     */
    @GetMapping(value = "/revoke", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TextMessage> revokeCertificate(@RequestParam(value = "serialNumber", required = true) String serialNumber,
                                                         @RequestParam(value = "revokeReason", required = true) String revokeReason) throws Exception {


        System.out.println("REVOKE REQUEST FOR SERIAL " + serialNumber);
        loggerService.print("Request: \"Revoke certificate\" received");
        TextMessage textMessage = certificateRevokeService.revokeCertificate(serialNumber, RevokeReason.toEnum(revokeReason));
        return new ResponseEntity<>(textMessage, HttpStatus.OK);
    }
}
