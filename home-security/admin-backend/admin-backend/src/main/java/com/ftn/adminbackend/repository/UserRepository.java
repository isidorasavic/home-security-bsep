package com.ftn.adminbackend.repository;

import com.ftn.adminbackend.model.User;
import com.ftn.adminbackend.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndDeletedIsFalseAndBlockedIsFalse(String username);

    Optional<User> findByIdAndDeletedIsFalseAndBlockedIsFalse(long id);

    Optional<User> findByIdAndDeletedIsFalse(long id);

    List<User> findByRoleAndDeletedIsFalse(@Param("role") UserRole role);

    List<User> findByRoleNotAndDeletedIsFalse(@Param("role") String role);

    List<User> findByDeletedIsFalseAndRoleIsNot(@Param("role") UserRole role);

    List<User> findByRoleNotAndDeletedIsFalseAndUsernameContaining(@Param("role") UserRole role, @Param("search") String search);

    List<User> findByRoleNotAndDeletedIsFalse(@Param("role") UserRole role);
  }
  
