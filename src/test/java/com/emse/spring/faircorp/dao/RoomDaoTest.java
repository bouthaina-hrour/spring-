package com.emse.spring.faircorp.dao;


import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RoomDaoTest {


    @Autowired
    RoomDao roomDao;


    @Test
    public void shouldFindRoomByName() {
        Room room =roomDao.findByName("Room1");
        Assertions.assertThat(room.getId()).isEqualTo(-10l);

    }}
