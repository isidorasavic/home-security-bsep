package com.ftn.adminbackend.repository;

import java.util.Optional;

import com.ftn.adminbackend.model.RegularUser;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegularUserRepository extends MongoRepository<RegularUser, Integer>{
    
    Optional<RegularUser> findByUsername(String username);

}
