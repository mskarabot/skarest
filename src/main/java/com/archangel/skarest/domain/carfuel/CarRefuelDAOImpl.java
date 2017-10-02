package com.archangel.skarest.domain.carfuel;

import com.google.cloud.datastore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarRefuelDAOImpl implements CarRefuelDAO {

    private static final String ENTITY_NAME = "CarRefuel";

    private final Logger log = LoggerFactory.getLogger(CarRefuelDAOImpl.class);

    @Autowired
    private Datastore datastore;

    @Override
    public long create(CarRefuel carRefuel) {
        Transaction transaction = datastore.newTransaction();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind(ENTITY_NAME);
        try {
            Key key = datastore.allocateId(keyFactory.newKey());
            Entity entityOut = map(carRefuel, key);
            datastore.put(entityOut);
            transaction.commit();
            return key.getId();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Batch.Response create(CarRefuelList carRefuelList) {
        Batch batch = datastore.newBatch();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind(ENTITY_NAME);
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
            KeyFactory keyFactory = datastore.newKeyFactory().setKind(ENTITY_NAME);
            Entity entitySource = transaction.get(keyFactory.newKey(carRefuel.getId()));
            if (entitySource != null) {
                Entity entityWrite = map(entitySource, carRefuel);
                transaction.put(entityWrite);
            }
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<CarRefuel> list() {
        Query<Entity> query =
                Query.newEntityQueryBuilder().setKind(ENTITY_NAME).build();
        Iterator<Entity> entitiesIterator = datastore.run(query);
        ArrayList<CarRefuel> arrayList = new ArrayList();
        while (entitiesIterator.hasNext()) {
            Entity e = entitiesIterator.next();
            CarRefuel carRefuel = map(e);
            arrayList.add(carRefuel);
        }
        return arrayList;
    }


    @Override
    public void delete(long id) {
        KeyFactory keyFactory = datastore.newKeyFactory().setKind(ENTITY_NAME);
        Key key = keyFactory.newKey(id);
        datastore.delete(key);
    }


    private Entity map(CarRefuel carRefuel, Key key) {
        return Entity.newBuilder(key)
                .set("fuelPricePerLiter", carRefuel.getFuelPricePerLiter())
                .set("kilometers", carRefuel.getKilometers())
                .set("liters", carRefuel.getLiters())
                .set("totalPrice", carRefuel.getTotalPrice())
                .build();
    }

    private Entity map(Entity entityFrom, CarRefuel carRefuel) {
        return Entity.newBuilder(entityFrom)
                .set("fuelPricePerLiter", carRefuel.getFuelPricePerLiter())
                .set("kilometers", carRefuel.getKilometers())
                .set("liters", carRefuel.getLiters())
                .set("totalPrice", carRefuel.getTotalPrice())
                .build();
    }

    private CarRefuel map(Entity entityFrom) {
        CarRefuel carRefuel = null;
        if (entityFrom != null) {
            carRefuel = new CarRefuel();
            carRefuel.setId(entityFrom.getKey().getId());
            carRefuel.setFuelPricePerLiter(entityFrom.getLong("fuelPricePerLiter"));
            carRefuel.setKilometers(entityFrom.getLong("kilometers"));
            carRefuel.setLiters(entityFrom.getLong("liters"));
            carRefuel.setTotalPrice(entityFrom.getLong("totalPrice"));
        }
        return carRefuel;
    }

}
