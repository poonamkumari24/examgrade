package com.examgrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examgrade.entity.Quiz;
import com.examgrade.entity.User;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    // Find all quizzes created by a specific teacher
    List<Quiz> findByCreatedBy(User createdBy);
}