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

    public Long create(CarRefuel dto) {
        Entity entity = new Entity(CAR_REFUEL_KIND);
        entity = CarRefuelMapper.dtoToEntity(dto, entity);
        Key key = datastoreService.put(entity);
        log.info("CarRefuel created", dto);
        return key.getId();
    }

    public CarRefuel get(Long id) {
        try {
            Entity entity = datastoreService.get(KeyFactory.createKey(CAR_REFUEL_KIND, id));
            log.info("CarRefuel loaded", entity);
            return CarRefuelMapper.entityToDto(entity);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void update(CarRefuel dto) {
        Key key = KeyFactory.createKey(CAR_REFUEL_KIND, dto.getId());
        Entity entity = new Entity(key);
        entity.setProperty(CarRefuel.FUEL_PRICE_PER_LITER, dto.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.LITERS, dto.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.TOTAL_PRICE, dto.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.KILOMETERS, dto.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.DESCRIPTION, dto.getFuelPricePerLiter());
        datastoreService.put(entity);
        log.info("CarRefuel updated", dto);
    }

    public void delete(Long id) {
        Key key = KeyFactory.createKey(CAR_REFUEL_KIND, id);
        datastoreService.delete(key);
        log.info("CarRefuel deleted", id);
    }

    private List<CarRefuel> entitiesToCarRefuels(Iterator<Entity> entityResults) {
        List<CarRefuel> results = new ArrayList<>();
        while (entityResults.hasNext()) {  // We still have data
            results.add(CarRefuelMapper.entityToDto(entityResults.next()));      // Add the Book to the List
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
