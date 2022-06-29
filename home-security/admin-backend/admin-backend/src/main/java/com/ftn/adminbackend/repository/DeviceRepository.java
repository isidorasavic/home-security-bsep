package com.ftn.adminbackend.repository;

import com.ftn.adminbackend.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findAllByObject_Id(@Param("object_id") long object_id);
}