package com.ftn.MyHousebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ftn.MyHousebackend.model.Object;

import java.util.List;

@Repository
public interface ObjectRepository extends JpaRepository<Object, Long> {

    List<Object> findAllByOwnerId(@Param("owner_id") long owner_id);
}
