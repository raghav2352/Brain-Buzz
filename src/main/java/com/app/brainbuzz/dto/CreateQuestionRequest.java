package com.app.brainbuzz.dto;

import java.util.List;

import com.app.brainbuzz.model.QuestionType;

import lombok.Data;

@Data
public class CreateQuestionRequest {
    private Long sessionId;
    private String text;
    private QuestionType type;
    private List<String> options;
}
