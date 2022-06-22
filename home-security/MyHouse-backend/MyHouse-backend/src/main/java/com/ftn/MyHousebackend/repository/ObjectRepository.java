package com.ftn.MyHousebackend.repository;

import com.ftn.MyHousebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ftn.MyHousebackend.model.Object;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObjectRepository extends JpaRepository<Object, Long> {

    Optional<Object> findById(@Param("id") long id);

    List<Object> findAllByOwnerId(@Param("owner") long owner);

    List<Object> findAllByOwnerIdNot(@Param("owner") long owner);
}
