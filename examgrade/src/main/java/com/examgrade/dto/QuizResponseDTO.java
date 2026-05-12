package com.examgrade.dto;

public class QuizResponseDTO {

    private Long id;
    private String title;
    private String subject;
    private String classGroup;
    private String description;
    private Integer totalMarks;
    private Integer durationMinutes;
    private Integer passingScore;
    private Integer questionCount;

    public QuizResponseDTO() {
    }

    public QuizResponseDTO(
            Long id,
            String title,
            String subject,
            String classGroup,
            String description,
            Integer totalMarks,
            Integer durationMinutes,
            Integer passingScore,
            Integer questionCount) {

        this.id = id;
        this.title = title;
        this.subject = subject;
        this.classGroup = classGroup;
        this.description = description;
        this.totalMarks = totalMarks;
        this.durationMinutes = durationMinutes;
        this.passingScore = passingScore;
        this.questionCount = questionCount;
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

    public String getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(String classGroup) {
        this.classGroup = classGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(Integer totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Integer getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(Integer passingScore) {
        this.passingScore = passingScore;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }
}
