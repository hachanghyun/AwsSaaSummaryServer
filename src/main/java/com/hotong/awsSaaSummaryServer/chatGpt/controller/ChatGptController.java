package com.hotong.awsSaaSummaryServer.chatGpt.controller;

import com.hotong.awsSaaSummaryServer.chatGpt.entity.Exam;
import com.hotong.awsSaaSummaryServer.chatGpt.entity.Subtopic;
import com.hotong.awsSaaSummaryServer.chatGpt.entity.Topic;
import com.hotong.awsSaaSummaryServer.chatGpt.model.request.BotRequest;
import com.hotong.awsSaaSummaryServer.chatGpt.model.response.ChatGptResponse;
import com.hotong.awsSaaSummaryServer.chatGpt.repository.SubtopicRepository;
import com.hotong.awsSaaSummaryServer.chatGpt.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hotong.awsSaaSummaryServer.chatGpt.repository.TopicRepository;
import com.hotong.awsSaaSummaryServer.chatGpt.repository.ExamRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
public class ChatGptController {

    public ChatGptController(ChatGptService botService, ExamRepository examRepository) {
        this.botService = botService;
        this.examRepository = examRepository;
    }

    @Autowired
    private ChatGptService botService;

    @Autowired
    private final ExamRepository examRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private SubtopicRepository subtopicRepository;

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @PostMapping("/send")
    public ChatGptResponse sendMessage(@RequestBody BotRequest botRequest) {
        return botService.askQuestion(botRequest);
    }

    @PostMapping("/test")
    public ChatGptResponse sendMessageTest(@RequestBody BotRequest botRequest) {
        return botService.askQuestionTest(botRequest);
    }

    @PostMapping("/answer")
    public ChatGptResponse sendMessageAnswer(@RequestBody BotRequest botRequest) {
        return botService.askQuestionAnswer(botRequest);
    }

    @PostMapping("/original")
    public ChatGptResponse sendMessageOriginal(@RequestBody BotRequest botRequest) {
        return botService.askQuestionOriginal(botRequest);
    }

    @PostMapping("/original2")
    public ChatGptResponse sendMessageOriginal2(@RequestBody BotRequest botRequest) {
        return botService.askQuestionOriginal2(botRequest);
    }

    @GetMapping("/{id}")
    public Optional<Exam> getExamById(@PathVariable("id") Long id) {
        return examRepository.findById(id);
    }

    @GetMapping("/{topicId}/subtopics")
    public List<Subtopic> getSubtopicsByTopic(@PathVariable("topicId") Long topicId) {
        return subtopicRepository.findByTopicId(topicId);
    }

    @GetMapping("/suball")
    public List<Subtopic> getAllSubtopics() {
        return subtopicRepository.findAll();
    }
}



