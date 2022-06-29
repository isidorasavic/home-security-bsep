package com.ftn.adminbackend.repository;

import com.ftn.adminbackend.model.Certificate;
import com.ftn.adminbackend.model.ValidityPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ValidityPeriodRepository extends JpaRepository<ValidityPeriod, Long> {
    ValidityPeriod findByCertificate(Certificate c);

    //  EXAMPLES OF PARAMETERIZED QUERY
    @Query("select vp from ValidityPeriod vp where vp.certificate = ?1")
    ValidityPeriod getValidityPeriodCertificate(Certificate c);
}
