package com.ftn.adminbackend.model.builders;

import com.ftn.adminbackend.model.Certificate;
import com.ftn.adminbackend.model.enums.CertificateStatus;
import com.ftn.adminbackend.model.enums.EntityType;
import com.ftn.adminbackend.model.enums.RevokeReason;

public class CertificateBuilder {
    private Long id;
    private String serialNumber;
    private boolean isActive;
    private boolean isCA;
    private CertificateStatus certificateStatus;
    private RevokeReason revokeReason;
    private String issuedTo;
    private String issuedBy;
    private EntityType entityType;

    public CertificateBuilder setEntityType(EntityType entityType) {
        this.entityType = entityType;
        return this;
    }

    public CertificateBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CertificateBuilder setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public CertificateBuilder setIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CertificateBuilder setIsCA(boolean isCA) {
        this.isCA = isCA;
        return this;
    }

    public CertificateBuilder setCertificateStatus(CertificateStatus certificateStatus) {
        this.certificateStatus = certificateStatus;
        return this;
    }

    public CertificateBuilder setRevokeReason(RevokeReason revokeReason) {
        this.revokeReason = revokeReason;
        return this;
    }

    public CertificateBuilder setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
        return this;
    }

    public CertificateBuilder setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
        return this;
    }

    public Certificate createCertificate() {
        return new Certificate(id, serialNumber, isActive, isCA, certificateStatus, revokeReason, issuedTo, issuedBy, entityType);
    }
}
