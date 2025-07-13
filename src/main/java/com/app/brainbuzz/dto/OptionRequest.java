package com.app.brainbuzz.dto;

import lombok.Data;

@Data
public class OptionRequest {
    private String text;
    private Long questionId;
}
