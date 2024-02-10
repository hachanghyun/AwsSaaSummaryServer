package com.hotong.awsSaaSummaryServer.chatGpt.controller;

import com.hotong.awsSaaSummaryServer.chatGpt.entity.Subtopic;
import com.hotong.awsSaaSummaryServer.chatGpt.entity.Topic;
import com.hotong.awsSaaSummaryServer.chatGpt.model.request.BotRequest;
import com.hotong.awsSaaSummaryServer.chatGpt.model.response.ChatGptResponse;
import com.hotong.awsSaaSummaryServer.chatGpt.repository.SubtopicRepository;
import com.hotong.awsSaaSummaryServer.chatGpt.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hotong.awsSaaSummaryServer.chatGpt.repository.TopicRepository;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class BotController {

    @Autowired
    private BotService botService;

    @PostMapping("/send")
    public ChatGptResponse sendMessage(@RequestBody BotRequest botRequest) {
        return botService.askQuestion(botRequest);
    }

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Autowired
    private SubtopicRepository subtopicRepository;

    @GetMapping("/{topicId}/subtopics")
    public List<Subtopic> getSubtopicsByTopic(@PathVariable("topicId") Long topicId) {
        return subtopicRepository.findByTopicId(topicId);
    }
}



