package com.archangel.skarest.domain.qalearn;

import com.archangel.skarest.domain.util.Result;
import com.google.appengine.api.datastore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.archangel.skarest.domain.qalearn.QuestionAnswerMapper.dtoToEntity;
import static com.archangel.skarest.domain.qalearn.QuestionAnswerMapper.entityToDto;

@Service
public class QuestionAnswerDAOImpl implements QuestionAnswerDAO {

    private static final String QA_KIND = "QuestionAnswer";

    private final Logger log = LoggerFactory.getLogger(QuestionAnswerDAOImpl.class);

    private DatastoreService datastoreService;

    @Autowired
    public QuestionAnswerDAOImpl(DatastoreService datastoreService) {
        this.datastoreService = datastoreService;
    }

    public Long create(QuestionAnswer dto) {
        Entity entity = new Entity(QA_KIND);
        dtoToEntity(dto, entity);
        Key key = datastoreService.put(entity);
        log.info("QuestionAnswer created", dto);
        return key.getId();
    }

    public QuestionAnswer get(Long id) {
        try {
            Entity entity = datastoreService.get(KeyFactory.createKey(QA_KIND, id));
            log.info("QuestionAnswer loaded", entity);
            return entityToDto(entity);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void update(QuestionAnswer dto) {
        Key key = KeyFactory.createKey(QA_KIND, dto.getId());
        Entity entity = new Entity(key);
        dtoToEntity(dto, entity);
        datastoreService.put(entity);
        log.info("QuestionAnswer updated", dto);
    }

    public void delete(Long id) {
        Key key = KeyFactory.createKey(QA_KIND, id);
        datastoreService.delete(key);
        log.info("QuestionAnswer deleted", id);
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
                .addSort(QuestionAnswer.BOOK_CODE, Query.SortDirection.ASCENDING);
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
