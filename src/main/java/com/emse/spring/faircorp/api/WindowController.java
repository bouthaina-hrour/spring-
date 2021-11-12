package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/windows")
@Transactional
public class WindowController {
    @Autowired
    WindowDao windowDao;
    @Autowired
    RoomDao roomDao;

    @GetMapping // (5)
    public List<WindowDto> findAll() {

        return windowDao.findAll().stream()
                .map(WindowDto::new)
                .collect(Collectors.toList());  // (6)
    }
    @GetMapping(path = "/{id}")
    WindowDto findById(@PathVariable Long id){
        return windowDao.findById(id).map(WindowDto::new).orElse(null);
    }


    @PostMapping
    public WindowDto create(@RequestBody  WindowDto dto){
        Room room =roomDao.getById(dto.getRoomId());
        Window window =null;

        if(dto.getId()==null){
            window=windowDao.save(new Window(dto.getName(), dto.getWindowStatus(), room));

        }
        else{
            window=windowDao.getById(dto.getId());
            window.setWindowStatus(dto.getWindowStatus());
            window.setName(dto.getName());
        }
        return new WindowDto(window);

    }

    @PutMapping(path = "/{id}/switch")
    public WindowDto switchStatus(@PathVariable Long id) {
        Window window = windowDao.findById(id).orElseThrow(IllegalArgumentException::new);
        window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN);
        return new WindowDto(window);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(id);
    }
}






