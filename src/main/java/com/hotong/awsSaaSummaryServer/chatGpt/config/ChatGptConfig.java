package com.hotong.awsSaaSummaryServer.chatGpt.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class ChatGptConfig {

    private String AUTHORIZATION = "Authorization";
    private String BEARER = "Bearer ";
    @Value("${myapp.gptKey}")
    private String API_KEY;
    private String MODEL = "gpt-3.5-turbo-instruct";
    private Integer MAX_TOKEN = 300;
    private Double TEMPERATURE = 0.0;
    private Double TOP_P = 1.0;
    private String MEDIA_TYPE = "application/json; charset=UTF-8";
    private String URL = "https://api.openai.com/v1/completions";
}
