package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/heaters")
@Transactional
public class HeaterController {
    @Autowired
    HeaterDao heaterDao;
    @Autowired
    RoomDao roomDao;

    /**
     * lists all heaters
     * @return the list of heaters
     * @see com.emse.spring.faircorp.dao.HeaterDao
     * @see com.emse.spring.faircorp.dto.HeaterDto
     */
    @GetMapping
    public List<HeaterDto> findAll(){
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());

    }

    /**
     * lists one specific heater
     * @param heater_id the id of heater you are searching for
     * @return the heaterdto if it is found and null if not
     * @see com.emse.spring.faircorp.dao.HeaterDao
     * @see com.emse.spring.faircorp.dto.HeaterDto
     */
    @GetMapping(path = "/{heater_id}")
    public HeaterDto findById(@PathVariable Long heater_id){
        return heaterDao.findById(heater_id).map(HeaterDto::new).orElse(null);
    }
    /**
     * creates a new heater in the api
     * @param dto the data transfer you want to add as request body
     * @return the heaterdto created
     * @see com.emse.spring.faircorp.model.Heater
     * @see com.emse.spring.faircorp.dao.HeaterDao
     */
    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto) {
        Room room = roomDao.getById(dto.getRoomId());

        Heater heater = null;
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater(dto.getName(), dto.getHeaterStatus(), room));
        } else {
            heater=heaterDao.getById(dto.getId());
            heater.setHeaterStatus(dto.getHeaterStatus());
            heater.setName(dto.getName());
            heater.setPower(dto.getPower());
        }
     return new HeaterDto(heater);
    }
    /**
     * deletes a specific heater
     * @param heater_id the id of heater you want to delete
     * @see com.emse.spring.faircorp.dao.HeaterDao
     * @see com.emse.spring.faircorp.dto.HeaterDto
     */
    @DeleteMapping(path = "/{heater_id}")
    public void delete(@PathVariable Long heater_id) {
        heaterDao.deleteById(heater_id);
    }
}
