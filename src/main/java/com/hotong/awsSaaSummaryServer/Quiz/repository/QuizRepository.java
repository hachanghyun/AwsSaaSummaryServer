package com.hotong.awsSaaSummaryServer.Quiz.repository;

import com.hotong.awsSaaSummaryServer.Quiz.entity.Quizzes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quizzes, Long> {
}

