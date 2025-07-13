package com.app.brainbuzz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.app.brainbuzz.dto.CreateQuestionRequest;
import com.app.brainbuzz.model.Question;
import com.app.brainbuzz.service.QuestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin
public class QuestionController {
    private final QuestionService questionService;
    
    @PostMapping
    public ResponseEntity<Question> createQuestion( @RequestBody CreateQuestionRequest request) {
        Question question = questionService.createQuestion(request);
        return ResponseEntity.ok(question);
    } 

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<Question>> getQuestionsBySession(@PathVariable Long sessionId){
        return ResponseEntity.ok(questionService.getQuestionsBySession(sessionId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id){
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Question updatedQuestion = questionService.updateQuestion(id, updates);
        return ResponseEntity.ok(updatedQuestion);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Question deleted successfully");
    }

}
