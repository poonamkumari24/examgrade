package com.examgrade.service;

import java.util.List;

import com.examgrade.dto.QuizAttemptDTO;
import com.examgrade.dto.QuizResponseDTO;
import com.examgrade.dto.QuizResultDTO;
import com.examgrade.dto.QuizSubmissionDTO;

public interface StudentQuizService {

    List<QuizResponseDTO> getAllAvailableQuizzes();
    QuizAttemptDTO getQuizForAttempt(Long quizId);
      QuizResultDTO submitQuiz(Long quizId, QuizSubmissionDTO submission);
}
