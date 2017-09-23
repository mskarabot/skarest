package com.archangel.skarest.domain;

import com.archangel.skarest.domain.carfuel.CarRefuel;
import com.archangel.skarest.domain.carfuel.CarRefuelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MainResponseController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private CarRefuelDAO carRefuelDAO;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/create")
    public CarRefuel createCarRefuel(@RequestParam(value = "km", defaultValue = "1000") long km) {
        CarRefuel carRefuel = new CarRefuel();
        carRefuel.setKilometers(km);
        carRefuel.setFuelPricePerLiter(0);
        carRefuel.setTotalPrice(0);
        carRefuel.setLiters(0);

        carRefuelDAO.create(carRefuel);

        return carRefuel;
    }

}