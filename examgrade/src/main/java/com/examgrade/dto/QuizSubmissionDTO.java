package com.examgrade.dto;

import java.util.Map;

public class QuizSubmissionDTO {

    private Long quizId;

    // questionId -> selected optionId
    private Map<Long, Long> answers;

    public QuizSubmissionDTO() {
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Map<Long, Long> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, Long> answers) {
        this.answers = answers;
    }
}
