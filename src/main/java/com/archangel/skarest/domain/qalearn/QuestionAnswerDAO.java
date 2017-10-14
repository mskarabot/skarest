package com.archangel.skarest.domain.qalearn;

import com.archangel.skarest.domain.util.Result;

/**
 * Created by skmi on 10. 10. 2017.
 */
public interface QuestionAnswerDAO {

    Long create(QuestionAnswer dao);

    QuestionAnswer get(Long id);

    void update(QuestionAnswer dao);

    void delete(Long id);

    Result<QuestionAnswer> find(String startCursorString, int limit);

}
