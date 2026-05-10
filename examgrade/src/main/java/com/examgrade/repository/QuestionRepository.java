package com.examgrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examgrade.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
