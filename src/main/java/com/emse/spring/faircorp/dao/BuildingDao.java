package com.emse.spring.faircorp.dao;
import com.emse.spring.faircorp.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * the class that make information exchange between building infos in database and the application
 */
public interface BuildingDao extends JpaRepository<Building,Long > ,BuildingDaoCustom{




}
