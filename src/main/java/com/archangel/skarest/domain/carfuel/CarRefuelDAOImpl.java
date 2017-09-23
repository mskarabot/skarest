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
        Key key = datastore.allocateId(keyFactory.newKey());
        Entity entityOut = map(carRefuel, key);
        datastore.put(entityOut);
        return key.id();
    }

    @Override
    public Batch.Response create(CarRefuelList carRefuelList) {
        Batch batch = datastore.newBatch();
        for (CarRefuel carRefuel : carRefuelList.getCarRefuels()) {
            Key key = datastore.allocateId(keyFactory.newKey());
            batch.put(map(carRefuel, key));
        }
        return batch.submit();
    }

    @Override
    public void update(CarRefuel carRefuel) {
        Transaction transaction = datastore.newTransaction();
        try {
            Entity entitySource = transaction.get(keyFactory.newKey(carRefuel.getId()));
            if (entitySource != null) {
                Entity entityWrite = map(entitySource, carRefuel);
                transaction.put(entityWrite);
            }
            transaction.commit();
        } finally {
            if (transaction.active()) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void deleteUser(long id) {
        Key key = keyFactory.newKey(id);
        datastore.delete(key);
    }


    private Entity map(CarRefuel carRefuel, Key key) {
        return Entity.builder(key)
                .set("fuelPricePerLiter", carRefuel.getFuelPricePerLiter())
                .set("kilometers", carRefuel.getKilometers())
                .set("liters", carRefuel.getLiters())
                .set("totalPrice", carRefuel.getTotalPrice())
                .build();
    }

    private Entity map(Entity entityFrom, CarRefuel carRefuel) {
        return Entity.builder(entityFrom)
                .set("fuelPricePerLiter", carRefuel.getFuelPricePerLiter())
                .set("kilometers", carRefuel.getKilometers())
                .set("liters", carRefuel.getLiters())
                .set("totalPrice", carRefuel.getTotalPrice())
                .build();
    }

}
