package com.examgrade.dto;

public class OptionAttemptDTO {

    private Long id;
    private String optionText;

    public OptionAttemptDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
