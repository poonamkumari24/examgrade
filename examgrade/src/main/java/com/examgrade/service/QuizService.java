package com.examgrade.service;

import com.examgrade.dto.QuizRequest;
import com.examgrade.entity.Quiz;

public interface QuizService {

    // Save a complete quiz with all questions and options
    Quiz createQuiz(QuizRequest quizRequest, String teacherEmail);
}
