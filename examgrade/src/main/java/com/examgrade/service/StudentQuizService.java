package com.examgrade.service;

import java.util.List;

import com.examgrade.dto.QuizAttemptDTO;
import com.examgrade.dto.QuizResponseDTO;

public interface StudentQuizService {

    List<QuizResponseDTO> getAllAvailableQuizzes();
    QuizAttemptDTO getQuizForAttempt(Long quizId);
}
