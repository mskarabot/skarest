package com.archangel.skarest.domain.carfuel;


import com.google.cloud.datastore.Batch;

import java.util.List;

public interface CarRefuelDAO {

    long create(CarRefuel carRefuel);

    Batch.Response create(CarRefuelList carRefuelList);

    void update(CarRefuel carRefuel);

    void delete(long id);

    List<CarRefuel> list();

}
