package com.ftn.adminbackend.repository;

import java.util.List;
import java.util.Optional;

import com.ftn.adminbackend.model.CSR;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSRRepository extends MongoRepository<CSR, Integer>{
    

    List<CSR> findByIsDeletedIsFalse();

    Optional<CSR> findBysnAndIsDeletedIsFalse(String sn);

}
