package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDayTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int currentHour = localDateTime.getHour();
        if (6 <= currentHour && currentHour < 12) {
            return new Morning();
        } else if (12 <= currentHour && currentHour < 18) {
            return new Day();
        } else if (18 <= currentHour && currentHour < 23) {
            return new Evening();
        } else {
            return new Night();
        }
    }
}
// END
