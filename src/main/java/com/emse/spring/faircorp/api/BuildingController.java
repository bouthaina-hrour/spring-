package com.emse.spring.faircorp.api;


import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.dto.Roomdto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/buildings")
@Transactional
public class BuildingController {

    @Autowired
    BuildingDao buildingDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    WindowDao windowDao;
    @Autowired
    HeaterDao heaterDao;

    /**
     * lists all buildings
     * @return the list of buildings
     * @see com.emse.spring.faircorp.dao.BuildingDao
     * @see com.emse.spring.faircorp.dto.BuildingDto
     */
    @GetMapping
    public List<BuildingDto> findAll() {

        return buildingDao.findAll().stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
    }
    /**
     * creates a new building in the api
     * @param buildingDto the data transfer you want to add as request body
     * @return the buildingdto created
     * @see com.emse.spring.faircorp.model.Building
     * @see com.emse.spring.faircorp.dao.BuildingDao
     */
    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto buildingDto){
        Building building=null;
        if(buildingDto.getId()==null){
            building=buildingDao.save(new Building(buildingDto.getOutsideTemperature()));

        }else {
            building=buildingDao.getById(buildingDto.getId());
            buildingDto.setOutsideTemperature(buildingDto.getOutsideTemperature());

        }
        return new BuildingDto(building);
    }
    /**
     * lists one specific building
     * @param building_id the id of building you are searching for
     * @return the buildingdto if it is found and null if not
     * @see com.emse.spring.faircorp.dao.BuildingDao
     * @see com.emse.spring.faircorp.dto.BuildingDto
     */
    @GetMapping(path = "/{building_id}")
    BuildingDto findById(@PathVariable Long building_id){
        return buildingDao.findById(building_id).map(BuildingDto::new).orElse(null);
    }
    /**
     * deletes a specific building
     * deletes also all his {@link com.emse.spring.faircorp.model.Room} and {@link com.emse.spring.faircorp.model.Heater} and {@link com.emse.spring.faircorp.model.Window}
     * @param building_id the id of building you want to delete
     * @see com.emse.spring.faircorp.dao.BuildingDao
     * @see com.emse.spring.faircorp.dto.BuildingDto
     * @see com.emse.spring.faircorp.model.Window
     * @see com.emse.spring.faircorp.model.Room
     * @see com.emse.spring.faircorp.model.Heater
     * @see com.emse.spring.faircorp.model.Building
     */
    @DeleteMapping(path = "/{building_id}")
    void deleteById(@PathVariable Long building_id){
        List<Room> rooms=roomDao.findByBuildingId(building_id);
        List<Window> windows=buildingDao.findByBuildingId(building_id);
        List<Heater> heaters = buildingDao.findHeatersByBuildingId(building_id);
        for (Window window:windows) {
            windowDao.deleteById(window.getId());

        }
        for (Heater heater:heaters) {
            heaterDao.deleteById(heater.getId());

        }
        for (Room room:rooms) {
            roomDao.deleteById(room.getId());

        }

        buildingDao.deleteById(building_id);
    }



}
