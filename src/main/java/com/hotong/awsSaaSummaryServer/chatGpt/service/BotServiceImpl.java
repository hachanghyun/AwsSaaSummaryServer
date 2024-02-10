package com.hotong.awsSaaSummaryServer.chatGpt.service;

import com.hotong.awsSaaSummaryServer.chatGpt.config.ChatGptConfig;
import com.hotong.awsSaaSummaryServer.chatGpt.model.request.BotRequest;
import com.hotong.awsSaaSummaryServer.chatGpt.model.request.ChatGptRequest;
import com.hotong.awsSaaSummaryServer.chatGpt.model.response.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BotServiceImpl implements BotService {

    private static RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ChatGptConfig chatGptConfig;

    //    Build headers
    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(chatGptConfig.getMEDIA_TYPE()));
        headers.add(chatGptConfig.getAUTHORIZATION(), chatGptConfig.getBEARER() + chatGptConfig.getAPI_KEY());
        return new HttpEntity<>(chatRequest, headers);
    }

    //    Generate response
    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatRequestHttpEntity) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                chatGptConfig.getURL(),
                chatRequestHttpEntity,
                ChatGptResponse.class);

        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestion(BotRequest botRequest) {
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequest(
                                chatGptConfig.getMODEL(),
                                botRequest.getMessage(),
                                chatGptConfig.getTEMPERATURE(),
                                chatGptConfig.getMAX_TOKEN(),
                                chatGptConfig.getTOP_P())));
    }
}






