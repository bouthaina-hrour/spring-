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

    @GetMapping
    public List<BuildingDto> findAll() {

        return buildingDao.findAll().stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
    }
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
    @GetMapping(path = "/{building_id}")
    BuildingDto findById(@PathVariable Long building_id){
        return buildingDao.findById(building_id).map(BuildingDto::new).orElse(null);
    }
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
