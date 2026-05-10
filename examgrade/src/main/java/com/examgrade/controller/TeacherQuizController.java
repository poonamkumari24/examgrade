package com.examgrade.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examgrade.dto.QuizRequest;
import com.examgrade.entity.Quiz;
import com.examgrade.service.QuizService;

@RestController
@RequestMapping("/api/teacher/quizzes")
@CrossOrigin(origins = "*")
public class TeacherQuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping
    public Map<String, Object> createQuiz(
            @RequestBody QuizRequest quizRequest,
            Authentication authentication) {

        // Get logged-in teacher email from JWT
        String teacherEmail = authentication.getName();

        // Save quiz
        Quiz savedQuiz = quizService.createQuiz(quizRequest, teacherEmail);

        // Response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Quiz created successfully");
        response.put("quizId", savedQuiz.getId());

        return response;
    }
}
