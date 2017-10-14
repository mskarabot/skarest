package com.archangel.skarest.domain.carfuel;

import com.google.appengine.api.datastore.Entity;

/**
 * Created by Mihael on 14.10.2017.
 */
public abstract class CarRefuelMapper {

    private CarRefuelMapper() {
    }

    public static CarRefuel entityToDto(Entity entity) {
        return new CarRefuel.Builder()
                .id(entity.getKey().getId())
                .fuelPricePerLiter((Long) entity.getProperty(CarRefuel.FUEL_PRICE_PER_LITER))
                .liters((Long) entity.getProperty(CarRefuel.LITERS))
                .totalPrice((Long) entity.getProperty(CarRefuel.TOTAL_PRICE))
                .kilometers((Long) entity.getProperty(CarRefuel.KILOMETERS))
                .description((String) entity.getProperty(CarRefuel.DESCRIPTION))
                .build();
    }

    public static Entity dtoToEntity(CarRefuel carRefuel, Entity entity) {
        entity.setProperty(CarRefuel.FUEL_PRICE_PER_LITER, carRefuel.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.LITERS, carRefuel.getLiters());
        entity.setProperty(CarRefuel.TOTAL_PRICE, carRefuel.getTotalPrice());
        entity.setProperty(CarRefuel.KILOMETERS, carRefuel.getKilometers());
        entity.setProperty(CarRefuel.DESCRIPTION, carRefuel.getDescription());
        return entity;
    }

}
