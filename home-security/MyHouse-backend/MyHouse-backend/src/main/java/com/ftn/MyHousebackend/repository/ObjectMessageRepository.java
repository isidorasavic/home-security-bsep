package com.ftn.MyHousebackend.repository;

import com.ftn.MyHousebackend.model.ObjectMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectMessageRepository  extends JpaRepository<ObjectMessage, Long> {

    List<ObjectMessage> findAllByObject_IdOrderByDate(@Param("object_id") long object_id);

    List<ObjectMessage> findAllByDevice_IdOrderByDate(@Param("device_id") long device_id);

}
