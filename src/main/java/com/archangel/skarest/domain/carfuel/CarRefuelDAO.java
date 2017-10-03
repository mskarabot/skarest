package com.archangel.skarest.domain.carfuel;


import com.archangel.skarest.domain.util.Result;

public interface CarRefuelDAO {

    Long create(CarRefuel carRefuel);

    CarRefuel get(Long id);

    void update(CarRefuel carRefuel);

    void delete(Long id);

    Result<CarRefuel> find(String startCursorString, int limit);

}
