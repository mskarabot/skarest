package com.archangel.skarest.service;

import com.archangel.skarest.domain.carfuel.CarRefuel;
import com.archangel.skarest.domain.carfuel.CarRefuelDAO;
import com.archangel.skarest.domain.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by skmi on 3. 10. 2017.
 */
@RestController
@RequestMapping("/carRefuels")
public class CarRefuelRestController {

    private CarRefuelDAO carRefuelDAO;

    @Autowired
    public CarRefuelRestController(CarRefuelDAO carRefuelDAO) {
        this.carRefuelDAO = carRefuelDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<CarRefuel> get(@PathVariable Long id) {
        CarRefuel carRefuel = carRefuelDAO.get(id);
        if (carRefuel == null) {
            throw new RuntimeException("No data found");
        }
        return new ResponseEntity<>(carRefuel, HttpStatus.OK);
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CarRefuel> create(@RequestBody CarRefuel carRefuelIn) {
        CarRefuel carRefuel = new CarRefuel.Builder()
                .kilometers(carRefuelIn.getKilometers())
                .fuelPricePerLiter(carRefuelIn.getFuelPricePerLiter())
                .totalPrice(carRefuelIn.getTotalPrice())
                .liters(carRefuelIn.getLiters())
                .description(carRefuelIn.getDescription())
                .build();

        carRefuelDAO.create(carRefuel);

        return new ResponseEntity<>(carRefuel, HttpStatus.OK);
    }

    // UPDATE
    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public void update(@PathVariable Long id, @RequestBody CarRefuel carRefuelIn) {
        carRefuelIn.setId(id);
        carRefuelDAO.update(carRefuelIn);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id) {
        carRefuelDAO.delete(id);
    }

    // FIND
    @RequestMapping(method = RequestMethod.GET)
    public Result<CarRefuel> find(@RequestParam(value = "cursor", required = false) String cursor, @RequestParam(value = "limit", defaultValue = "10") int limit) {
        return carRefuelDAO.find(cursor, limit);
    }

}
