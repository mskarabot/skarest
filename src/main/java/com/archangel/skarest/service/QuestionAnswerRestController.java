package com.archangel.skarest.service;

import com.archangel.skarest.domain.qalearn.QuestionAnswer;
import com.archangel.skarest.domain.qalearn.QuestionAnswerDAO;
import com.archangel.skarest.domain.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by skmi on 3. 10. 2017.
 */
@RestController
@RequestMapping("/questionAnswers")
public class QuestionAnswerRestController {

    private QuestionAnswerDAO dao;

    @Autowired
    public QuestionAnswerRestController(QuestionAnswerDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<QuestionAnswer> get(@PathVariable Long id) {
        QuestionAnswer dto = this.dao.get(id);
        if (dto == null) {
            throw new RuntimeException("No data found");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<QuestionAnswer> create(@RequestBody QuestionAnswer dtoIn) {
        Long id = dao.create(dtoIn);
        dtoIn.setId(id);
        return new ResponseEntity<>(dtoIn, HttpStatus.OK);
    }

    // UPDATE
    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public void update(@PathVariable Long id, @RequestBody QuestionAnswer dtoIn) {
        dtoIn.setId(id);
        dao.update(dtoIn);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id) {
        dao.delete(id);
    }

    // FIND
    @RequestMapping(method = RequestMethod.GET)
    public Result<QuestionAnswer> find(@RequestParam(value = "cursor", required = false) String cursor, @RequestParam(value = "limit", defaultValue = "10") int limit) {
        return dao.find(cursor, limit);
    }

}
