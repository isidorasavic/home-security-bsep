package com.ftn.adminbackend.repository;

import com.ftn.adminbackend.model.SystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SystemEntityRepository extends JpaRepository<SystemEntity, Long> {
    SystemEntity findByUid(String valueOf);
}
