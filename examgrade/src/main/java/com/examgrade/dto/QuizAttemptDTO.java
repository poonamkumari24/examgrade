package com.examgrade.dto;

import java.util.List;

public class QuizAttemptDTO {

    private Long id;
    private String title;
    private String subject;
    private String description;
    private Integer durationMinutes;
    private List<QuestionAttemptDTO> questions;

    public QuizAttemptDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public List<QuestionAttemptDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionAttemptDTO> questions) {
        this.questions = questions;
    }
}
