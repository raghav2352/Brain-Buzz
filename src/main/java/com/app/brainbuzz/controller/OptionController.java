package com.app.brainbuzz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.brainbuzz.dto.OptionRequest;
import com.app.brainbuzz.model.Option;
import com.app.brainbuzz.model.Question;
import com.app.brainbuzz.repository.OptionRepository;
import com.app.brainbuzz.repository.QuestionRepository;
import com.app.brainbuzz.service.OptionService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class OptionController {
    
    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionService optionService;

    @GetMapping("/{questionId}/options")
    public ResponseEntity<List<Option>> getOptionsByQuestionsId(@PathVariable Long questionId){
        List<Option> options = optionRepository.findByQuestionId(questionId);
        return ResponseEntity.ok(options);
    }

    @PostMapping("/questions/{questionId}/options")
    public ResponseEntity<Option> createOption(@PathVariable Long questionId , @RequestBody OptionRequest optionRequest){
        Option createdOption = optionService.createOption(questionId, optionRequest);
        return ResponseEntity.ok(createdOption);
    }

    @PatchMapping("/options/{optionId}")
    public ResponseEntity<Option> updateOption(@PathVariable Long optionId , @RequestBody OptionRequest optionRequest){
        Option updatedOption = optionService.updateOption(optionId, optionRequest);
        return ResponseEntity.ok(updatedOption);
    }
    
    @DeleteMapping("/{optionId}")
    public ResponseEntity<String> deleteOption(@PathVariable Long optionId) {
        optionService.deleteOption(optionId);
        return ResponseEntity.ok("Option deleted successfully");
    }
}
