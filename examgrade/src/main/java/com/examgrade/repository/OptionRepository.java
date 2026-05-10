package com.examgrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examgrade.entity.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {

}
