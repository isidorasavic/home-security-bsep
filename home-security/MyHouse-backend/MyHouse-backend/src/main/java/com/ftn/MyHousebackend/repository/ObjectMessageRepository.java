package com.ftn.MyHousebackend.repository;

import com.ftn.MyHousebackend.model.ObjectMessage;
import com.ftn.MyHousebackend.model.enums.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ObjectMessageRepository  extends JpaRepository<ObjectMessage, Long> {

    List<ObjectMessage> findAllByObject_IdOrderByDate(@Param("object_id") long object_id);

    List<ObjectMessage> findAllByDevice_IdOrderByDate(@Param("device_id") long device_id);

    List<ObjectMessage> findAllByObject_IdAndDateIsAfterAndDateIsBeforeAndMessageTypeIsNotOrderByDate(
            @Param("object_id")long object_id, @Param("date_from")LocalDate date_from,
            @Param("date_to") LocalDate date_to, @Param("message_type") MessageType message_type);

}
