package com.archangel.skarest.domain.carfuel;

import com.archangel.skarest.domain.util.Result;
import com.google.appengine.api.datastore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarRefuelDAOImpl implements CarRefuelDAO {

    private static final String CAR_REFUEL_KIND = "CarRefuel";

    private final Logger log = LoggerFactory.getLogger(CarRefuelDAOImpl.class);

    private DatastoreService datastoreService;

    @Autowired
    public CarRefuelDAOImpl(DatastoreService datastoreService) {
        this.datastoreService = datastoreService;
    }

    private CarRefuel entityToCarRefule(Entity entity) {
        return new CarRefuel.Builder()
                .id(entity.getKey().getId())
                .fuelPricePerLiter((Long) entity.getProperty(CarRefuel.FUEL_PRICE_PER_LITER))
                .liters((Long) entity.getProperty(CarRefuel.LITERS))
                .totalPrice((Long) entity.getProperty(CarRefuel.TOTAL_PRICE))
                .kilometers((Long) entity.getProperty(CarRefuel.KILOMETERS))
                .description((String) entity.getProperty(CarRefuel.DESCRIPTION))
                .build();
    }

    public Long create(CarRefuel carRefuel) {
        Entity entity = new Entity(CAR_REFUEL_KIND);
        entity.setProperty(CarRefuel.FUEL_PRICE_PER_LITER, carRefuel.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.LITERS, carRefuel.getLiters());
        entity.setProperty(CarRefuel.TOTAL_PRICE, carRefuel.getTotalPrice());
        entity.setProperty(CarRefuel.KILOMETERS, carRefuel.getKilometers());
        entity.setProperty(CarRefuel.DESCRIPTION, carRefuel.getDescription());

        Key key = datastoreService.put(entity);
        return key.getId();
    }

    public CarRefuel get(Long id) {
        try {
            Entity entity = datastoreService.get(KeyFactory.createKey(CAR_REFUEL_KIND, id));
            return entityToCarRefule(entity);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void update(CarRefuel carRefuel) {
        Key key = KeyFactory.createKey(CAR_REFUEL_KIND, carRefuel.getId());
        Entity entity = new Entity(key);
        entity.setProperty(CarRefuel.FUEL_PRICE_PER_LITER, carRefuel.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.LITERS, carRefuel.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.TOTAL_PRICE, carRefuel.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.KILOMETERS, carRefuel.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.DESCRIPTION, carRefuel.getFuelPricePerLiter());

        datastoreService.put(entity);
    }

    public void delete(Long id) {
        Key key = KeyFactory.createKey(CAR_REFUEL_KIND, id);
        datastoreService.delete(key);
    }

    private List<CarRefuel> entitiesToCarRefuels(Iterator<Entity> entityResults) {
        List<CarRefuel> results = new ArrayList<>();
        while (entityResults.hasNext()) {  // We still have data
            results.add(entityToCarRefule(entityResults.next()));      // Add the Book to the List
        }
        return results;
    }

    public Result<CarRefuel> find(String startCursorString, int limit) {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(limit); // Only show 10 at a time
        if (startCursorString != null && !startCursorString.equals("")) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
        }
        Query query = new Query(CAR_REFUEL_KIND)
                .addSort(CarRefuel.KILOMETERS, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery = datastoreService.prepare(query);
        QueryResultIterator<Entity> entityResults = preparedQuery.asQueryResultIterator(fetchOptions);

        List<CarRefuel> results = entitiesToCarRefuels(entityResults);     // Retrieve and convert Entities
        Cursor cursor = entityResults.getCursor();              // Where to start next time
        if (cursor != null && results.size() == 10) {         // Are we paging? Save Cursor
            String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
            return new Result<>(results, cursorString, limit);
        } else {
            return new Result<>(results, limit);
        }
    }

//    public Result<Book> listBooksByUser(String userId, String startCursorString) {
//        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(10); // Only show 10 at a time
//        if (startCursorString != null && !startCursorString.equals("")) {
//            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
//        }
//        Query query = new Query(BOOK_KIND) // We only care about Books
//                // Only for this user
//                .setFilter(new Query.FilterPredicate(
//                        Book.CREATED_BY_ID, Query.FilterOperator.EQUAL, userId))
//                // a custom datastoreService index is required since you are filtering by one property
//                // but ordering by another
//                .addSort(Book.TITLE, Query.SortDirection.ASCENDING);
//        PreparedQuery preparedQuery = datastoreService.prepare(query);
//        QueryResultIterator<Entity> results = preparedQuery.asQueryResultIterator(fetchOptions);
//
//        List<Book> resultBooks = entitiesToBooks(results);     // Retrieve and convert Entities
//        Cursor cursor = results.getCursor();              // Where to start next time
//        if (cursor != null && resultBooks.size() == 10) {         // Are we paging? Save Cursor
//            String cursorString = cursor.toWebSafeString();               // Cursors are WebSafe
//            return new Result<>(resultBooks, cursorString);
//        } else {
//            return new Result<>(resultBooks);
//        }
//    }

}
