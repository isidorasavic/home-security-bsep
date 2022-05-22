package com.ftn.MyHousebackend.repository;

import java.util.Optional;

import com.ftn.MyHousebackend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);
  
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndDeletedIsFalse(String username);
  }
  
