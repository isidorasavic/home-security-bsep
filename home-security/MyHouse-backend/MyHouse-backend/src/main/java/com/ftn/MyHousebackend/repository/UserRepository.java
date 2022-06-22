package com.ftn.MyHousebackend.repository;

import java.util.List;
import java.util.Optional;

import com.ftn.MyHousebackend.model.User;

import com.ftn.MyHousebackend.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);
  
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndDeletedIsFalse(String username);

    List<User> findByRoleNotAndDeletedIsFalse(@Param("role") String role);

    List<User> findByDeletedIsFalseAndRoleIsNot(@Param("role") String role);

    List<User> findByRoleNotAndDeletedIsFalseAndUsernameContaining(@Param("role") String role, @Param("search") String search);

    List<User> findByRoleNotAndDeletedIsFalse(@Param("role") UserRole role);
  }
  
