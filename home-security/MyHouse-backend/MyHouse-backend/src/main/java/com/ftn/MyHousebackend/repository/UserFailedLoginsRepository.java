package com.ftn.MyHousebackend.repository;

import com.ftn.MyHousebackend.model.UserFailedLogins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFailedLoginsRepository extends JpaRepository<UserFailedLogins, Long> {

    Optional<UserFailedLogins> findByUser_Id(@Param("user_id") long user_id);
}
