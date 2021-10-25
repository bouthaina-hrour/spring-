package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BuildingDaoTest {

    @Autowired
    BuildingDao buildingDao;

    @Test
    public void shouldShowBuildingWindows() {




        List<Window> windows = buildingDao.findByBuildingId(-10L);

        Assertions.assertThat(windows.size()).isEqualTo(4);


    }

}
