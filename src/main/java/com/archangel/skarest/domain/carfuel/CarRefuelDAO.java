package com.archangel.skarest.domain.carfuel;


public interface CarRefuelDAO {

    Long createCarRefuel(CarRefuel carRefuel);

    CarRefuel readCarRefuel(Long id);

    void updateCarRefuel(CarRefuel carRefuel);

    void deleteCarRefuel(Long id);

    Result<CarRefuel> listCarRefuel(String startCursorString);


}
