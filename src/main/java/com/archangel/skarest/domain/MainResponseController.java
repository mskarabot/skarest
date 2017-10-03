package com.archangel.skarest.domain;

import com.archangel.skarest.domain.carfuel.CarRefuel;
import com.archangel.skarest.domain.carfuel.CarRefuelDAO;
import com.archangel.skarest.domain.carfuel.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String indexResponse() {
        return "Springboot-appengine-standard running...";
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/create")
    public CarRefuel createCarRefuel(@RequestParam(value = "km", defaultValue = "1000") long km) {
        CarRefuel carRefuel = new CarRefuel.Builder()
                .kilometers(km)
                .fuelPricePerLiter(0L)
                .totalPrice(0L)
                .liters(0L)
                .build();

        carRefuelDAO.createCarRefuel(carRefuel);

        return carRefuel;
    }

    @RequestMapping("/list")
    public Result<CarRefuel> listCarRefuel(@RequestParam(value = "cursor") String cursor) {
        return carRefuelDAO.listCarRefuel(cursor);
    }

}