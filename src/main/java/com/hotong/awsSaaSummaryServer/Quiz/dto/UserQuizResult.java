package com.hotong.awsSaaSummaryServer.Quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserQuizResult {
    private Long quizId;
    private String question;
    private String selectedOption;
    private String correctAnswer;
    private boolean isCorrect;

    public UserQuizResult(Long quizId, String question, String selectedOption,
                          String correctAnswer, boolean isCorrect) {
        this.quizId = quizId;
        this.question = question;
        this.selectedOption = selectedOption;
        this.correctAnswer = correctAnswer;
        this.isCorrect = isCorrect;
    }

}

