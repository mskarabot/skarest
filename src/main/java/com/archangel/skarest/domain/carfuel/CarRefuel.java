package com.archangel.skarest.domain.carfuel;

/**
 * Created by Mihael on 23.9.2017.
 */
public class CarRefuel {

    private Long id;
    private Long fuelPricePerLiter;
    private Long liters;
    private Long totalPrice;
    private Long kilometers;
    private String description;

    public static final String ID = "id";
    public static final String FUEL_PRICE_PER_LITER = "fuelPricePerLiter";
    public static final String LITERS = "liters";
    public static final String TOTAL_PRICE = "totalPrice";
    public static final String KILOMETERS = "kilometers";
    public static final String DESCRIPTION = "description";

    public CarRefuel() {
    }

    private CarRefuel(Builder builder) {
        this.id = builder.id;
        this.fuelPricePerLiter = builder.fuelPricePerLiter;
        this.liters = builder.liters;
        this.totalPrice = builder.totalPrice;
        this.kilometers = builder.kilometers;
        this.description = builder.description;
    }

    public static class Builder {
        private Long id;
        private Long fuelPricePerLiter;
        private Long liters;
        private Long totalPrice;
        private Long kilometers;
        private String description;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder fuelPricePerLiter(Long fuelPricePerLiter) {
            this.fuelPricePerLiter = fuelPricePerLiter;
            return this;
        }

        public Builder liters(Long liters) {
            this.liters = liters;
            return this;
        }

        public Builder totalPrice(Long totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder kilometers(Long kilometers) {
            this.kilometers = kilometers;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public CarRefuel build() {
            return new CarRefuel(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFuelPricePerLiter() {
        return fuelPricePerLiter;
    }

    public void setFuelPricePerLiter(Long fuelPricePerLiter) {
        this.fuelPricePerLiter = fuelPricePerLiter;
    }

    public Long getLiters() {
        return liters;
    }

    public void setLiters(Long liters) {
        this.liters = liters;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public void setKilometers(Long kilometers) {
        this.kilometers = kilometers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CarRefuel{" +
                "id=" + id +
                ", fuelPricePerLiter=" + fuelPricePerLiter +
                ", liters=" + liters +
                ", totalPrice=" + totalPrice +
                ", kilometers=" + kilometers +
                ", description='" + description + '\'' +
                '}';
    }
}
