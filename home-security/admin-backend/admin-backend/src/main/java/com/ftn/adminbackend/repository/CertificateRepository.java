package com.ftn.adminbackend.repository;

import com.ftn.adminbackend.model.Certificate;
import com.ftn.adminbackend.model.enums.CertificateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    List<Certificate> findAll();

    Certificate findBySerialNumber(String serialNumber);

    Certificate save(Certificate certificate);

    void removeBySerialNumber(String serialNumber);

    List<Certificate> findByIsCAAndCertificateStatus(boolean ca, CertificateStatus status);

    @Query("select c from Certificate c where c.revokeReason is not null")
    List<Certificate> findRevokedCertificates();

    //  EXAMPLES OF PARAMETERIZED QUERY
    @Query("select c from Certificate c where c.serialNumber = ?1")
    Certificate getCertificateSerialNumber(String serialNumber);

    Certificate findOneBySerialNumber(String serialNumber);
}