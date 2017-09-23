package com.archangel.skarest.domain.carfuel;


import com.google.cloud.datastore.Batch;

public interface CarRefuelDAO {

    long create(CarRefuel carRefuel);

    Batch.Response create(CarRefuelList carRefuelList);

    void update(CarRefuel carRefuel);

    void deleteUser(long id);

}
