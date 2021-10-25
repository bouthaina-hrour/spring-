package com.emse.spring.faircorp.api;


import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.Roomdto;
import com.emse.spring.faircorp.model.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {

    @Autowired
    RoomDao roomDao;
    @Autowired
    WindowDao windowDao;
    @Autowired
    HeaterDao heaterDao;
    @Autowired
    BuildingDao buildingDao;
    @GetMapping
    public List<Roomdto> findAll(){
        return roomDao.findAll().stream().map(Roomdto::new).collect(Collectors.toList());
    }
    @GetMapping(path = "/{room_id}")
    public Roomdto findById(@PathVariable Long room_id){
        return roomDao.findById(room_id).map(Roomdto::new).orElse(null);
    }
    @PostMapping
    public Roomdto create(@RequestBody Roomdto roomdto){
        Building building=buildingDao.getById(roomdto.getBuildingId());
        Room room =null;
        if(roomdto.getId()==null){
            room=roomDao.save(new Room(roomdto.getName(),roomdto.getFloor(),building));

        }else {
            room=roomDao.getById(roomdto.getId());
            room.setFloor(roomdto.getFloor());
            room.setName(roomdto.getName());
            room.setCurrentTemperature(roomdto.getCurrentTemperature());
            room.setTargetTemperature(roomdto.getTargetTemperature());
        }
        return new Roomdto(room);
    }

    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id){

        List<Window> windows=windowDao.findWindows(room_id);
        List<Heater> heaters = heaterDao.findByRoomId(room_id);
        for (Window window:windows) {
            windowDao.deleteById(window.getId());

        }
        for (Heater heater:heaters) {
            heaterDao.deleteById(heater.getId());

        }

        roomDao.deleteById(room_id);
    }

    @PutMapping(path = "/{room_id}/switchWindow")
    public void switchWindows(@PathVariable Long room_id){



        List<Window> windows= windowDao.findWindows(room_id);
        for (Window window:windows){
            if(window.getWindowStatus()==WindowStatus.CLOSED )
            {
                window.setWindowStatus(WindowStatus.OPEN);}
           else if(window.getWindowStatus()==WindowStatus.OPEN){
                window.setWindowStatus(WindowStatus.CLOSED);
            }

        }





    }
    @PutMapping(path = "/{room_id}/switchHeaters")
    public void switchHeaters(@PathVariable Long room_id){



        List<Heater> heaters= heaterDao.findByRoomId(room_id);
        for (Heater heater:heaters){
            if(heater.getHeaterStatus()== HeaterStatus.OFF )
            {
                heater.setHeaterStatus(HeaterStatus.ON);}
            else if(heater.getHeaterStatus()== HeaterStatus.ON){
                heater.setHeaterStatus(HeaterStatus.OFF);
            }

        }

    }}
