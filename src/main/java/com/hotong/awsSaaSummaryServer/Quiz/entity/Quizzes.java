package com.hotong.awsSaaSummaryServer.Quiz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "quizzes")
public class Quizzes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    // Getter와 Setter
}

