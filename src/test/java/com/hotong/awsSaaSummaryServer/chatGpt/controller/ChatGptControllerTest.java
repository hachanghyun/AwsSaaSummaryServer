package com.hotong.awsSaaSummaryServer.chatGpt.controller;

import com.hotong.awsSaaSummaryServer.chatGpt.entity.Exam;
import com.hotong.awsSaaSummaryServer.chatGpt.entity.Subtopic;
import com.hotong.awsSaaSummaryServer.chatGpt.entity.Topic;
import com.hotong.awsSaaSummaryServer.chatGpt.model.request.BotRequest;
import com.hotong.awsSaaSummaryServer.chatGpt.model.response.ChatGptResponse;
import com.hotong.awsSaaSummaryServer.chatGpt.service.ChatGptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.hotong.awsSaaSummaryServer.chatGpt.repository.ExamRepository;
import com.hotong.awsSaaSummaryServer.chatGpt.repository.SubtopicRepository;
import com.hotong.awsSaaSummaryServer.chatGpt.repository.TopicRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

@WebMvcTest(ChatGptController.class)
@ExtendWith(MockitoExtension.class)
public class ChatGptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatGptService botService;

    @MockBean
    private ExamRepository examRepository;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private SubtopicRepository subtopicRepository;

    @InjectMocks
    private ChatGptController chatGptController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(chatGptController).build();
    }

    @Test
    public void testGetAllTopics() throws Exception {
        Topic topic1 = new Topic(1L, "Topic 1");
        Topic topic2 = new Topic(2L, "Topic 2");
        List<Topic> topics = Arrays.asList(topic1, topic2);

        when(topicRepository.findAll()).thenReturn(topics);

        mockMvc.perform(get("/api/topics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Topic 1"))
                .andExpect(jsonPath("$[1].name").value("Topic 2"));
    }

    @Test
    public void testSendMessage() throws Exception {
        BotRequest request = new BotRequest("Hello");
        ChatGptResponse response = new ChatGptResponse("Hi there!");

        when(botService.askQuestion(any(BotRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/topics/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\": \"Hello\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("Hi there!"));
    }

    @Test
    public void testGetExamById() throws Exception {
        Exam exam = new Exam(1L, "Exam 1");

        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));

        mockMvc.perform(get("/api/topics/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Exam 1"));
    }

    @Test
    public void testGetSubtopicsByTopic() throws Exception {
        Subtopic subtopic1 = new Subtopic(1L, "Subtopic 1", 1L);
        Subtopic subtopic2 = new Subtopic(2L, "Subtopic 2", 1L);
        List<Subtopic> subtopics = Arrays.asList(subtopic1, subtopic2);

        when(subtopicRepository.findByTopicId(1L)).thenReturn(subtopics);

        mockMvc.perform(get("/api/topics/1/subtopics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Subtopic 1"))
                .andExpect(jsonPath("$[1].name").value("Subtopic 2"));
    }

    @Test
    public void testGetAllSubtopics() throws Exception {
        Subtopic subtopic1 = new Subtopic(1L, "Subtopic 1", 1L);
        Subtopic subtopic2 = new Subtopic(2L, "Subtopic 2", 2L);
        List<Subtopic> subtopics = Arrays.asList(subtopic1, subtopic2);

        when(subtopicRepository.findAll()).thenReturn(subtopics);

        mockMvc.perform(get("/api/topics/suball"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Subtopic 1"))
                .andExpect(jsonPath("$[1].name").value("Subtopic 2"));
    }
}
