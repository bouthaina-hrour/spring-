package com.emse.spring.faircorp.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public interface GreetingService {
    void greet(String name);
}
