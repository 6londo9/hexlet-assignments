package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    private MyApplicationConfig config;
    @Autowired
    private Meal meal;

    @GetMapping("/daytime")
    public String bonAppetit() {
        String daytime = config.getDayTime().getName();
        return "It is " + daytime + " now. "
                + "Enjoy your " + meal.getMealForDaytime(daytime);
    }
}
// END
