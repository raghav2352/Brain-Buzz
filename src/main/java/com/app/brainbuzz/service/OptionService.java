package com.app.brainbuzz.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.brainbuzz.dto.OptionRequest;
import com.app.brainbuzz.model.ClassroomSession;
import com.app.brainbuzz.model.Option;
import com.app.brainbuzz.model.Question;
import com.app.brainbuzz.model.QuestionType;
import com.app.brainbuzz.repository.OptionRepository;
import com.app.brainbuzz.repository.QuestionRepository;
import com.app.brainbuzz.repository.SessionRepository;

import jakarta.transaction.Transactional;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;
    private final SessionRepository sessionRepository;

    public Option createOption(Long questionId, OptionRequest optionRequest) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Option option = new Option();
        option.setText(optionRequest.getText());
        option.setVoteCount(0); 
        option.setQuestion(question);

        return optionRepository.save(option);
    }

    public Option updateOption(Long optionId, OptionRequest optionRequest) {
        Option option = optionRepository.findById(optionId)
            .orElseThrow(() -> new RuntimeException("Option not found"));

        if(optionRequest.getText() != null) option.setText(optionRequest.getText());

        if(optionRequest.getQuestionId() != null){
            Question question = questionRepository.findById(optionRequest.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Questions not found"));
            option.setQuestion(question);
        }

        return optionRepository.save(option);
    }

    public void deleteOption(Long optionId){
        Option option = optionRepository.findById(optionId)
        .orElseThrow(() -> new RuntimeException("Option not found"));

        optionRepository.deleteById(optionId);
    }


}
