package com.archangel.skarest.domain.carfuel;

import com.google.cloud.datastore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CarRefuelDAOImpl implements CarRefuelDAO {

    private static final String ENTITY_NAME = "CarRefuel";

    private final Logger log = LoggerFactory.getLogger(CarRefuelDAOImpl.class);

    @Autowired
    private Datastore datastore;

    private KeyFactory keyFactory;

    @PostConstruct
    public void initializeKeyFactories() {
        log.info("Initializing key factories");
        keyFactory = datastore.newKeyFactory().kind("CarRefuel");
    }

    @Override
    public long create(CarRefuel carRefuel) {
        Key key = keyFactory.newKey(ENTITY_NAME);
        Entity entityOut = map(carRefuel, key);
        datastore.put(entityOut);
        return key.id();
    }

    @Override
    public Batch.Response create(CarRefuelList carRefuelList) {
        Batch batch = datastore.newBatch();
        for (CarRefuel carRefuel : carRefuelList.getCarRefuels()) {
            Key key = keyFactory.newKey(ENTITY_NAME);
            batch.put(map(carRefuel, key));
        }
        return batch.submit();
    }

    @Override
    public void update(CarRefuel carRefuel) {
        datastore.put(map(carRefuel, null));
    }


    @Override
    public void deleteUser(long id) {
        Key key = keyFactory.newKey(id);
        datastore.delete(key);
    }


    private Entity map(CarRefuel carRefuel, Key key) {
        if (key == null) {
            key = keyFactory.newKey(carRefuel.getId());
        }
        return Entity.builder(key)
                .set("fuelPricePerLiter", carRefuel.getFuelPricePerLiter())
                .set("kilometers", carRefuel.getKilometers())
                .set("liters", carRefuel.getLiters())
                .set("totalPrice", carRefuel.getTotalPrice())
                .build();
    }

}
