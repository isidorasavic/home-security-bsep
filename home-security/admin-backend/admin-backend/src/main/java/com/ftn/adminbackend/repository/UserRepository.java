package com.ftn.adminbackend.repository;

import java.util.List;
import java.util.Optional;

import com.ftn.adminbackend.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer>{
	
	@Query("{username:?0, deleted:false, role:?1}")
	Optional<User> findAdminByUsernameAndDeletedIsFalseAndRole(String username, String role);

	Optional<User> findByUsername(String username);

    Optional<User> findById(long id);

    List<User> findAll();

}

