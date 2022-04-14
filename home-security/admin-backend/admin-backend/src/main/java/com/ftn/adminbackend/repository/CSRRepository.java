package com.ftn.adminbackend.repository;

import java.util.List;
import java.util.Optional;

import com.ftn.adminbackend.model.CSR;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSRRepository extends MongoRepository<CSRRepository, Integer>{
    

    List<CSR> findByIsDeletedIsFalse();

    Optional<CSR> findBySNAndIsDeletedIsFalse(String sn);

}
