package com.app.brainbuzz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.brainbuzz.model.Option;

public interface OptionRepository extends JpaRepository<Option , Long> {

    List<Option> findByQuestionId(Long questionId);
    
}
