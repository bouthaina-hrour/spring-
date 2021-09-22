package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DummyUserService implements UserService{
    @Autowired
    GreetingService gs;
    List<String> names= Arrays.asList("fatouma","khadouj");
    @Override
    public void greetAll() {
        for(String name :names){
            gs.greet(name);
        }

    }
}
