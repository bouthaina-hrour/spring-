package com.emse.spring.faircorp.hello;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
@ExtendWith(OutputCaptureExtension.class)
public class GreetingServiceTest {
    @Test
    public void greetingTest(CapturedOutput capturedOutput){
GreetingService greetingService=new ConsoleGreetingService();
greetingService.greet("spring");
Assertions.assertThat(capturedOutput.getAll()).contains("Hello, spring");
    }
}
