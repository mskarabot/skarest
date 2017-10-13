package com.archangel.skarest.domain.qalearn;

import com.archangel.skarest.domain.carfuel.CarRefuel;
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
public class QuestionAnswerDAOImpl implements QuestionAnswerDAO {

    private static final String QA_KIND = "QuestionAnswer";

    private final Logger log = LoggerFactory.getLogger(QuestionAnswerDAOImpl.class);

    private DatastoreService datastoreService;

    @Autowired
    public QuestionAnswerDAOImpl(DatastoreService datastoreService) {
        this.datastoreService = datastoreService;
    }

    private QuestionAnswer entityToDto(Entity entity) {
        return new QuestionAnswer.Builder()
                .id(entity.getKey().getId())
                .bookCode((String)entity.getProperty(QuestionAnswer.BOOK_CODE))
                .chapterCode((String)entity.getProperty(QuestionAnswer.CHAPTER_CODE))
                .questionCode((String)entity.getProperty(QuestionAnswer.QUESTION_CODE))
                .fuelPricePerLiter((Long) entity.getProperty(CarRefuel.FUEL_PRICE_PER_LITER))
                .liters((Long) entity.getProperty(CarRefuel.LITERS))
                .totalPrice((Long) entity.getProperty(CarRefuel.TOTAL_PRICE))
                .kilometers((Long) entity.getProperty(CarRefuel.KILOMETERS))
                .description((String) entity.getProperty(CarRefuel.DESCRIPTION))
                .build();
    }

    public Long create(QuestionAnswer dto) {
        Entity entity = new Entity(QA_KIND);
        entity.setProperty(CarRefuel.FUEL_PRICE_PER_LITER, dto.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.LITERS, dto.getLiters());
        entity.setProperty(CarRefuel.TOTAL_PRICE, dto.getTotalPrice());
        entity.setProperty(CarRefuel.KILOMETERS, dto.getKilometers());
        entity.setProperty(CarRefuel.DESCRIPTION, dto.getDescription());

        Key key = datastoreService.put(entity);
        return key.getId();
    }

    public QuestionAnswer get(Long id) {
        try {
            Entity entity = datastoreService.get(KeyFactory.createKey(QA_KIND, id));
            return entityToDto(entity);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void update(QuestionAnswer dto) {
        Key key = KeyFactory.createKey(QA_KIND, dto.getId());
        Entity entity = new Entity(key);
        entity.setProperty(CarRefuel.FUEL_PRICE_PER_LITER, dto.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.LITERS, dto.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.TOTAL_PRICE, dto.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.KILOMETERS, dto.getFuelPricePerLiter());
        entity.setProperty(CarRefuel.DESCRIPTION, dto.getFuelPricePerLiter());

        datastoreService.put(entity);
    }

    public void delete(Long id) {
        Key key = KeyFactory.createKey(QA_KIND, id);
        datastoreService.delete(key);
    }

    private List<QuestionAnswer> entitiesToDtos(Iterator<Entity> entityResults) {
        List<QuestionAnswer> results = new ArrayList<>();
        while (entityResults.hasNext()) {  // We still have data
            results.add(entityToDto(entityResults.next()));      // Add the dto to the List
        }
        return results;
    }

    public Result<QuestionAnswer> find(String startCursorString, int limit) {
        FetchOptions fetchOptions = FetchOptions.Builder.withLimit(limit); // Only show 10 at a time
        if (startCursorString != null && !startCursorString.equals("")) {
            fetchOptions.startCursor(Cursor.fromWebSafeString(startCursorString)); // Where we left off
        }
        Query query = new Query(QA_KIND)
                .addSort(CarRefuel.KILOMETERS, Query.SortDirection.ASCENDING);
        PreparedQuery preparedQuery = datastoreService.prepare(query);
        QueryResultIterator<Entity> entityResults = preparedQuery.asQueryResultIterator(fetchOptions);

        List<QuestionAnswer> results = entitiesToDtos(entityResults);     // Retrieve and convert Entities
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