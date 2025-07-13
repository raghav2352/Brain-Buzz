package com.app.brainbuzz.service;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.brainbuzz.dto.CreateQuestionRequest;
import com.app.brainbuzz.model.ClassroomSession;
import com.app.brainbuzz.model.Question;
import com.app.brainbuzz.model.QuestionType;
import com.app.brainbuzz.repository.OptionRepository;
import com.app.brainbuzz.repository.QuestionRepository;
import com.app.brainbuzz.repository.SessionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final SessionRepository sessionRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public Question createQuestion(CreateQuestionRequest request){
        ClassroomSession session = sessionRepository.findById(request.getSessionId()).orElseThrow(() -> new RuntimeException("Session not found"));

        Question question = Question.builder()
        .text(request.getText())
        .type(request.getType())
        .session(session)
        .build();

        Question savedQuestion = questionRepository.save(question);

        List<com.app.brainbuzz.model.Option> options = request.getOptions().stream()
        .map(opt -> com.app.brainbuzz.model.Option.builder()
        .text(opt)
        .question(savedQuestion)
        .build())
        .toList();

        optionRepository.saveAll(options);
        savedQuestion.setOptions(options);

        return savedQuestion;
    }

    public Question getQuestionById(Long id){
        return questionRepository.findById(id)
            .orElseThrow(() ->  new RuntimeException("Question not found with ID: " + id));    
    }

    public List<Question> getQuestionsBySession(Long sessionId){
        return questionRepository.findBySessionId(sessionId);
    }

    @Transactional
    public Question updateQuestion(Long id, Map<String, Object> updates) {
        Question question = questionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Question not found"));

        if (updates.containsKey("text")) {
                question.setText((String) updates.get("text"));
            }

        if (updates.containsKey("type")) {
                String typeStr = updates.get("type").toString().toUpperCase();
                question.setType(QuestionType.valueOf(typeStr));
            }

        if (updates.containsKey("sessionId")) {
            Long sessionId = ((Number) updates.get("sessionId")).longValue();
            ClassroomSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
            question.setSession(session);
            }

        return questionRepository.save(question);
    }


    public void deleteQuestion(Long id){
        Question question = questionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Question not found with ID: " + id));
        questionRepository.delete(question);
    }



}
