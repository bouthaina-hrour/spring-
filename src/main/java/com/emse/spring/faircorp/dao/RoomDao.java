package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * the class that make information exchange between room infos in database and the application
 */
public interface RoomDao extends JpaRepository<Room,Long> ,RoomDaoCustom {

    List<Room> findByBuildingId(Long id);
}
