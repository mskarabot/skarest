package com.archangel.skarest.domain.qalearn;

/**
 * Created by Mihael on 23.9.2017.
 */
public class QuestionAnswer {

    private Long id;
    private String bookCode;
    private String chapterCode;
    private String questionCode;
    private String question;
    private String answer;
    private Long numberOfAnsweredCorrect;
    private Long numberOfAnsweredWrong;

    public static final String ID = "id";
    public static final String BOOK_CODE = "bookCode";
    public static final String CHAPTER_CODE = "chapterCode";
    public static final String QUESTION_CODE = "questionCode";
    public static final String QUESTION = "question";
    public static final String ANSWER = "answer";
    public static final String NUM_OF_ANSWER_CORRECT = "numberOfAnsweredCorrect";
    public static final String NUM_OF_ANSWER_WRONG = "numberOfAnsweredWrong";

    public QuestionAnswer() {
    }

    private QuestionAnswer(Builder builder) {
        this.id = builder.id;
        this.bookCode = builder.bookCode;
        this.chapterCode = builder.chapterCode;
        this.questionCode = builder.questionCode;
        this.question = builder.question;
        this.answer = builder.answer;
        this.numberOfAnsweredCorrect = builder.numberOfAnsweredCorrect;
        this.numberOfAnsweredWrong = builder.numberOfAnsweredWrong;
    }

    public static class Builder {
        private Long id;
        private String bookCode;
        private String chapterCode;
        private String questionCode;
        private String question;
        private String answer;
        private Long numberOfAnsweredCorrect;
        private Long numberOfAnsweredWrong;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder bookCode(String bookCode) {
            this.bookCode = bookCode;
            return this;
        }

        public Builder chapterCode(String chapterCode) {
            this.chapterCode = chapterCode;
            return this;
        }

        public Builder questionCode(String questionCode) {
            this.questionCode = questionCode;
            return this;
        }


        public Builder question(String question) {
            this.question = question;
            return this;
        }

        public Builder answer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder numberOfAnsweredCorrect(Long numberOfAnsweredCorrect) {
            this.numberOfAnsweredCorrect = numberOfAnsweredCorrect;
            return this;
        }

        public Builder numberOfAnsweredWrong(Long numberOfAnsweredWrong) {
            this.numberOfAnsweredWrong = numberOfAnsweredWrong;
            return this;
        }

        public QuestionAnswer build() {
            return new QuestionAnswer(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getChapterCode() {
        return chapterCode;
    }

    public void setChapterCode(String chapterCode) {
        this.chapterCode = chapterCode;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getNumberOfAnsweredCorrect() {
        return numberOfAnsweredCorrect;
    }

    public void setNumberOfAnsweredCorrect(Long numberOfAnsweredCorrect) {
        this.numberOfAnsweredCorrect = numberOfAnsweredCorrect;
    }

    public Long getNumberOfAnsweredWrong() {
        return numberOfAnsweredWrong;
    }

    public void setNumberOfAnsweredWrong(Long numberOfAnsweredWrong) {
        this.numberOfAnsweredWrong = numberOfAnsweredWrong;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", bookCode='" + bookCode + '\'' +
                ", chapterCode='" + chapterCode + '\'' +
                ", questionCode='" + questionCode + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", numberOfAnsweredCorrect=" + numberOfAnsweredCorrect +
                ", numberOfAnsweredWrong=" + numberOfAnsweredWrong +
                '}';
    }
}
