package com.examgrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examgrade.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}