package com.hotong.awsSaaSummaryServer.chatGpt.repository;

import com.hotong.awsSaaSummaryServer.chatGpt.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
