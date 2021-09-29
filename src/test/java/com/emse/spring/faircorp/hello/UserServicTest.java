package com.emse.spring.faircorp.hello;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(OutputCaptureExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServicTest {
    @Configuration
    @ComponentScan("com.emse.spring.faircorp.hello")
    public static class DummyUserServiceTestConfig{}
@Autowired
    UserService us;

    @Test
    void testGreetingAll(CapturedOutput output){
        us.greetAll();
        Assertions.assertThat(output).contains("Hello, fatouma","Hello, khadouj");
    }


}
