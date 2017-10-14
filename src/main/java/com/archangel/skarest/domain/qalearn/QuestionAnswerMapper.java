package com.archangel.skarest.domain.qalearn;

import com.google.appengine.api.datastore.Entity;

/**
 * Created by Mihael on 13.10.2017.
 */
public abstract class QuestionAnswerMapper {

    private QuestionAnswerMapper() {
    }

    public static QuestionAnswer entityToDto(Entity entity) {
        return new QuestionAnswer.Builder()
                .id(entity.getKey().getId())
                .bookCode((String) entity.getProperty(QuestionAnswer.BOOK_CODE))
                .chapterCode((String) entity.getProperty(QuestionAnswer.CHAPTER_CODE))
                .questionCode((String) entity.getProperty(QuestionAnswer.QUESTION_CODE))
                .question((String) entity.getProperty(QuestionAnswer.QUESTION))
                .answer((String) entity.getProperty(QuestionAnswer.ANSWER))
                .numberOfAnsweredCorrect((Long) entity.getProperty(QuestionAnswer.NUM_OF_ANSWER_CORRECT))
                .numberOfAnsweredWrong((Long) entity.getProperty(QuestionAnswer.NUM_OF_ANSWER_WRONG))
                .build();
    }

    public static Entity dtoToEntity(QuestionAnswer dto, Entity entity) {
        entity.setProperty(QuestionAnswer.BOOK_CODE, dto.getBookCode());
        entity.setProperty(QuestionAnswer.CHAPTER_CODE, dto.getChapterCode());
        entity.setProperty(QuestionAnswer.QUESTION_CODE, dto.getQuestionCode());
        entity.setProperty(QuestionAnswer.QUESTION, dto.getQuestion());
        entity.setProperty(QuestionAnswer.ANSWER, dto.getAnswer());
        entity.setProperty(QuestionAnswer.NUM_OF_ANSWER_CORRECT, dto.getNumberOfAnsweredCorrect());
        entity.setProperty(QuestionAnswer.NUM_OF_ANSWER_WRONG, dto.getNumberOfAnsweredWrong());
        return entity;
    }

}
