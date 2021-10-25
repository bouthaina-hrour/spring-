package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class BuildingDaoCustomImpl implements BuildingDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    RoomDao roomDao;

    @Autowired
    WindowDao windowDao;

    @Override
    public List<Window> findByBuildingId(Long id) {
        List<Window> windows = new ArrayList<>();
        List<Room> rooms = roomDao.findByBuildingId(id);
        for (Room room : rooms) {

            windows.addAll(windowDao.findWindows(room.getId()));

        }
        return windows;


    }
}