package com.hotong.awsSaaSummaryServer.chatGpt.service;


import com.hotong.awsSaaSummaryServer.chatGpt.model.request.BotRequest;
import com.hotong.awsSaaSummaryServer.chatGpt.model.response.ChatGptResponse;

public interface ChatGptService {

    ChatGptResponse askQuestion(BotRequest botRequest);

    ChatGptResponse askQuestionTest(BotRequest botRequest);

    ChatGptResponse askQuestionAnswer(BotRequest botRequest);

    ChatGptResponse askQuestionOriginal(BotRequest botRequest);

    ChatGptResponse askQuestionOriginal2(BotRequest botRequest);
}
