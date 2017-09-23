package com.archangel.skarest.domain.carfuel;

import javax.validation.constraints.NotNull;

/**
 * Created by Mihael on 23.9.2017.
 */
public class CarRefuel {

    private long id;

    @NotNull
    private long fuelPricePerLiter;

    @NotNull
    private long liters;

    @NotNull
    private long totalPrice;

    @NotNull
    private long kilometers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFuelPricePerLiter() {
        return fuelPricePerLiter;
    }

    public void setFuelPricePerLiter(long fuelPricePerLiter) {
        this.fuelPricePerLiter = fuelPricePerLiter;
    }

    public long getLiters() {
        return liters;
    }

    public void setLiters(long liters) {
        this.liters = liters;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getKilometers() {
        return kilometers;
    }

    public void setKilometers(long kilometers) {
        this.kilometers = kilometers;
    }
}
