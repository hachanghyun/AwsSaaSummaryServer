package com.hotong.awsSaaSummaryServer.chatGpt.repository;

import com.hotong.awsSaaSummaryServer.chatGpt.entity.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {
    List<Subtopic> findByTopicId(Long topicId);
}
