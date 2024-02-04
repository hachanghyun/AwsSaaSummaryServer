package com.hotong.awsSaaSummaryServer.Quiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_answer_seq")
    @SequenceGenerator(name = "user_answer_seq", sequenceName = "user_answer_SEQ", allocationSize = 1)
    private Long id;
    private Long userId;
    private Long quizId;
    private String selectedOption;
    private boolean isCorrect;
    private String timestamp;
    // id, userId, quizId, selectedOption, isCorrect, timestamp
    // Getter와 Setter
}
