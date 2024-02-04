package com.hotong.awsSaaSummaryServer.Quiz.controller;

import com.hotong.awsSaaSummaryServer.Quiz.dto.UserQuizResult;
import com.hotong.awsSaaSummaryServer.Quiz.entity.Quizzes;
import com.hotong.awsSaaSummaryServer.Quiz.entity.UserAnswer;
import com.hotong.awsSaaSummaryServer.Quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizService quizService; // 서비스 계층을 사용하여 비즈니스 로직을 처리

    @GetMapping("/quizzes")
    public ResponseEntity<List<Quizzes>> getAllQuizzes() {
        List<Quizzes> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/useranswers")
    public ResponseEntity<?> saveUserAnswers(@RequestBody List<UserAnswer> userAnswers) {
        quizService.saveUserAnswers(userAnswers);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/useranswers/{userId}")
    public ResponseEntity<List<UserAnswer>> getUserAnswers(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(quizService.getUserAnswers(userId));
    }

    // 사용자 ID를 기반으로 퀴즈 결과를 가져오는 메서드
    @GetMapping("/results/{userId}")
    public ResponseEntity<List<UserQuizResult>> getUserQuizResults(@PathVariable("userId") Long userId) {
        List<UserQuizResult> results = quizService.getUserQuizResults(userId);
        return ResponseEntity.ok(results);
    }
}

