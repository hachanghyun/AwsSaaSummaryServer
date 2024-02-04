package com.hotong.awsSaaSummaryServer.Quiz.service;

import com.hotong.awsSaaSummaryServer.Quiz.dto.UserQuizResult;
import com.hotong.awsSaaSummaryServer.Quiz.entity.QQuizzes;
import com.hotong.awsSaaSummaryServer.Quiz.entity.QUserAnswer;
import com.hotong.awsSaaSummaryServer.Quiz.entity.Quizzes;
import com.hotong.awsSaaSummaryServer.Quiz.entity.UserAnswer;
import com.hotong.awsSaaSummaryServer.Quiz.repository.QuizRepository;
import com.hotong.awsSaaSummaryServer.Quiz.repository.UserAnswerRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository; // JPA 리포지토리를 사용

    public List<Quizzes> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    public void saveUserAnswers(List<UserAnswer> userAnswers) {
        for (UserAnswer userAnswer : userAnswers) {
            // 실제 정답 가져오기 (예시)
            Quizzes quizzes = quizRepository.findById(userAnswer.getQuizId()).orElse(null);
            if (quizzes != null) {
                // 사용자의 답과 실제 정답 비교
                Boolean isCorrect = quizzes.getAnswer().equals(userAnswer.getSelectedOption());
                userAnswer.setCorrect(isCorrect);
            }
        }

        userAnswerRepository.saveAll(userAnswers);
    }

    public List<UserAnswer> getUserAnswers(Long userId) {
        return userAnswerRepository.findByUserId(userId);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserQuizResult> getUserQuizResults(Long userId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QQuizzes quizzes = QQuizzes.quizzes;
        QUserAnswer userAnswer = QUserAnswer.userAnswer;

        return queryFactory
                .select(Projections.constructor(UserQuizResult.class,
                        quizzes.id, quizzes.question, quizzes.answer,
                        userAnswer.selectedOption, userAnswer.isCorrect))
                .from(userAnswer)
                .join(quizzes).on(userAnswer.quizId.eq(quizzes.id))
                .where(userAnswer.userId.eq(userId))
                .fetch();
    }

}

