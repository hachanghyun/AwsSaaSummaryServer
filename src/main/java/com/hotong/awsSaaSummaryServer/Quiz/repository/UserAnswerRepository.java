package com.hotong.awsSaaSummaryServer.Quiz.repository;

import com.hotong.awsSaaSummaryServer.Quiz.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findByUserId(Long userId);
}
