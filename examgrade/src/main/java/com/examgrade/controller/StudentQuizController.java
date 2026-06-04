package com.examgrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examgrade.dto.QuizAttemptDTO;
import com.examgrade.dto.QuizResponseDTO;
import com.examgrade.dto.QuizResultDTO;
import com.examgrade.dto.QuizSubmissionDTO;
import com.examgrade.service.StudentQuizService;

@RestController
@RequestMapping("/api/student/quizzes")
@CrossOrigin(origins = "*")
public class StudentQuizController {

    @Autowired
    private StudentQuizService studentQuizService;

    /**
     * GET /api/student/quizzes
     * Returns all quizzes for Available Quizzes page.
     */
    @GetMapping
    public List<QuizResponseDTO> getAllAvailableQuizzes() {
        return studentQuizService.getAllAvailableQuizzes();
    }

    /**
     * GET /api/student/quizzes/{quizId}
     * Returns full quiz data with questions and options.
     */
    @GetMapping("/{quizId}")
    public QuizAttemptDTO getQuizForAttempt(@PathVariable Long quizId) {
        return studentQuizService.getQuizForAttempt(quizId);
    }
    @PostMapping("/quizzes/{quizId}/submit")
public ResponseEntity<QuizResultDTO> submitQuiz(
        @PathVariable Long quizId,
        @RequestBody QuizSubmissionDTO submission) {

    QuizResultDTO result =
            studentQuizService.submitQuiz(quizId, submission);

    return ResponseEntity.ok(result);
}
}