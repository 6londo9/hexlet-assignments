package exercise.controller;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

//    @Autowired
//    private WeatherService weatherService;
//
//    // BEGIN
//    @GetMapping("/cities/{id}")
//    public String getCityWeather(@PathVariable("id") long id) {
//        City city = cityRepository.findById(id)
//                .orElseThrow(() -> new CityNotFoundException("City not found"));
//        String cityName = city.getName();
//        return weatherService.getWeatherByCity(cityName);
//    }
//
//    @GetMapping("/search")
//    public Iterable<City> getCities(@RequestParam String name) {
//        if (name == null) {
//            return cityRepository.findAllOrderByName();
//        }
//        return cityRepository.findAll();
//    }
    // END
}

