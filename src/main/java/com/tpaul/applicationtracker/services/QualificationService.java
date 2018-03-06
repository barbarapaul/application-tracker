package com.tpaul.applicationtracker.services;

import com.tpaul.applicationtracker.entities.Application;
import com.tpaul.applicationtracker.repositories.QuestionRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class QualificationService {

    private final QuestionRepository questionRepository;

    public QualificationService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public boolean applicationQualifies(@NonNull Application application) {
        return questionRepository.getQuestions()
                .stream()
                .allMatch(question -> application.getAnswers().stream()
                        .filter(answer -> answer.getQuestionId().equals(question.getId()))
                        .anyMatch(answer -> question.getAnswers().contains(answer.getAnswer())));
    }
}
