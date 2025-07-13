package com.app.brainbuzz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.brainbuzz.model.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>{
    List<Question> findBySessionId(Long sessionId);
    
}
