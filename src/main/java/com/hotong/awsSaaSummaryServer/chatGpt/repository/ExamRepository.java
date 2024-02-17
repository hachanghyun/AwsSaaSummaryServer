package com.hotong.awsSaaSummaryServer.chatGpt.repository;

import com.hotong.awsSaaSummaryServer.chatGpt.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamRepository extends JpaRepository<Exam, Long> {
}
