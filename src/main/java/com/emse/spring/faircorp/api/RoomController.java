package com.emse.spring.faircorp.api;


import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.Roomdto;
import com.emse.spring.faircorp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@CrossOrigin("http://localhost:8080")
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
    /**
     * lists all rooms
     * @return the list of rooms
     * @see com.emse.spring.faircorp.dao.RoomDao
     * @see com.emse.spring.faircorp.dto.Roomdto
     */
    @GetMapping
    public List<Roomdto> findAll(){
        return roomDao.findAll().stream().map(Roomdto::new).collect(Collectors.toList());
    }
    /**
     * lists one specific room
     * @param room_id the id of room you are searching for
     * @return the buildingdto if it is found and null if not
     * @see com.emse.spring.faircorp.dao.RoomDao
     * @see com.emse.spring.faircorp.dto.Roomdto
     */
    @GetMapping(path = "/{room_id}")
    public Roomdto findById(@PathVariable Long room_id){
        return roomDao.findById(room_id).map(Roomdto::new).orElse(null);
    }
    /**
     * creates a new room in the api
     * @param roomdto the data transfer you want to add as request body
     * @return the roomdto created
     * @see com.emse.spring.faircorp.model.Room
     * @see com.emse.spring.faircorp.dao.RoomDao
     */
    @PostMapping
    public Roomdto create(@RequestBody Roomdto roomdto){
        Building building=buildingDao.getById(roomdto.getBuildingId());
        Room room =null;
        if(roomdto.getId()==null){
            room=roomDao.save(new Room(roomdto.getFloor(),roomdto.getName(),roomdto.getCurrentTemperature(),roomdto.getTargetTemperature(),building));

        }else {
            room=roomDao.getById(roomdto.getId());
            room.setFloor(roomdto.getFloor());
            room.setName(roomdto.getName());
            room.setCurrentTemperature(roomdto.getCurrentTemperature());
            room.setTargetTemperature(roomdto.getTargetTemperature());
        }
        return new Roomdto(room);
    }
    /**
     * deletes a specific room
     * deletes also all his {@link com.emse.spring.faircorp.model.Window} and {@link com.emse.spring.faircorp.model.Heater}
     * @param room_id the id of room you want to delete
     * @see com.emse.spring.faircorp.dao.RoomDao
     * @see com.emse.spring.faircorp.dto.Roomdto
     * @see com.emse.spring.faircorp.model.Window
     * @see com.emse.spring.faircorp.model.Heater
     */
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
    /**
     * modifies the status of all windows of a specific room
     * @param room_id the id of what room you want to modify it windows' status
     * @see com.emse.spring.faircorp.model.Window
     * @see com.emse.spring.faircorp.dao.WindowDao
     * @see  com.emse.spring.faircorp.dto.WindowDto
     */
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
    /**
     * modifies the status of all heaters of a specific room
     * @param room_id the id of what room you want to modify it heaters' status
     * @see com.emse.spring.faircorp.model.Window
     * @see com.emse.spring.faircorp.dao.WindowDao
     * @see  com.emse.spring.faircorp.dto.WindowDto
     */
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
