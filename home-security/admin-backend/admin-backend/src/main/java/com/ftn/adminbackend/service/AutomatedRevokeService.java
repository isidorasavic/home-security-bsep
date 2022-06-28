package com.ftn.adminbackend.service;

import com.ftn.adminbackend.model.enums.RevokeReason;
import com.ftn.adminbackend.repository.CertificateRepository;
import com.ftn.adminbackend.repository.ValidityPeriodRepository;
import org.springframework.stereotype.Service;


import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

@Service
public class AutomatedRevokeService {

    final LoggerService loggerService;

    final VerificationService verificationService;

    final CertificateRevokeService certificateRevokeService;

    final CertificateRepository certificateRepository;

    final ValidityPeriodRepository validityPeriodRepository;

    public AutomatedRevokeService(LoggerService loggerService, VerificationService verificationService, CertificateRevokeService certificateRevokeService, CertificateRepository certificateRepository, ValidityPeriodRepository validityPeriodRepository) {
        this.loggerService = loggerService;
        this.verificationService = verificationService;
        this.certificateRevokeService = certificateRevokeService;
        this.certificateRepository = certificateRepository;
        this.validityPeriodRepository = validityPeriodRepository;
    }

    public RevokeReason catchRevokeReason(Certificate cert, Certificate[] chain, com.ftn.adminbackend.model.Certificate c){

        loggerService.print("Checking if certificate is invalid in function catchRevokeReason()!");
        if (!verificationService.checkDate((X509Certificate) cert)){
            if(verificationService.expired((X509Certificate) cert)){
                loggerService.print("Certificate status EXPIRED in function catchRevokeReason()!");
                certificateRevokeService.revokeCertificate(c.getSerialNumber(), RevokeReason.EXPIRED);

                return RevokeReason.EXPIRED;
            }
        }
        else if (!verificationService.verifyChain(chain)){
            certificateRevokeService.revokeCertificate(c.getSerialNumber(), RevokeReason.CA_COMPROMISE);
            loggerService.print("Invalid parent certificate in function catchRevokeReason() - CA compromised");

            return RevokeReason.CA_COMPROMISE;
        }
        else if (!verificationService.verifyExpirationChain(chain)){
            certificateRevokeService.revokeCertificate(c.getSerialNumber(), RevokeReason.EXPIRED);
        }
        loggerService.print("Certificate valid!");
        if (!c.getIsActive()){
            c.setIsActive(true);
            certificateRepository.save(c);
        }

        return RevokeReason.NOT_REVOKED;
    }
}
