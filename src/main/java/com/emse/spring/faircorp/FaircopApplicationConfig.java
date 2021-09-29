package com.emse.spring.faircorp;

import com.emse.spring.faircorp.hello.GreetingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaircopApplicationConfig {
    @Bean
    public CommandLineRunner greetingCommandLine(){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

            }
        };

    }
}
