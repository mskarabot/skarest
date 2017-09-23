package com.archangel.skarest.domain.carfuel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Mihael on 23.9.2017.
 */
public class CarRefuelList {


    @NotNull
    @Valid
    private List<CarRefuel> carRefuels;

    public CarRefuelList() {
    }

    public List<CarRefuel> getCarRefuels() {
        return carRefuels;
    }

    public CarRefuelList setCarRefuels(List<CarRefuel> carRefuels) {
        this.carRefuels = carRefuels;
        return this;
    }
}
