package com.tpaul.applicationtracker.services;

import com.tpaul.applicationtracker.entities.Answer;
import com.tpaul.applicationtracker.entities.Application;
import com.tpaul.applicationtracker.entities.Question;
import com.tpaul.applicationtracker.repositories.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QualificationServiceTest {

    @Mock
    private QuestionRepository questionRepositoryMock;

    @InjectMocks
    private QualificationService qualificationService;

    @Test
    public void emptyQuestions() throws Exception {
        when(questionRepositoryMock.getQuestions()).thenReturn(Collections.emptyList());

        assertTrue(qualificationService.applicationQualifies(Application.builder()
                .name("name")
                .answers(Collections.emptyList())
                .build()));
    }

    @Test
    public void allAnswersMatch() throws Exception {
        when(questionRepositoryMock.getQuestions()).thenReturn(Arrays.asList(
                Question
                        .builder()
                        .id("questionId 1")
                        .answers(new HashSet<>(Arrays.asList("answer 1", "answer 2")))
                        .build(),
                Question
                        .builder()
                        .id("questionId 2")
                        .answers(new HashSet<>(Arrays.asList("answer 2", "answer 3")))
                        .build()));

        assertTrue(qualificationService.applicationQualifies(Application.builder()
                .name("name")
                .answers(Arrays.asList(Answer.builder()
                                .questionId("questionId 1")
                                .answer("answer 1")
                                .build(),
                        Answer.builder()
                                .questionId("questionId 2")
                                .answer("answer 2")
                                .build()))
                .build()));
    }

    @Test
    public void oneAnswerDoesNotMatch() throws Exception {
        when(questionRepositoryMock.getQuestions()).thenReturn(Arrays.asList(
                Question
                        .builder()
                        .id("questionId 1")
                        .answers(new HashSet<>(Arrays.asList("answer 1", "answer 2")))
                        .build(),
                Question
                        .builder()
                        .id("questionId 2")
                        .answers(new HashSet<>(Arrays.asList("answer 2", "answer 3")))
                        .build()));

        assertFalse(qualificationService.applicationQualifies(Application.builder()
                .name("name")
                .answers(Arrays.asList(Answer.builder()
                                .questionId("questionId 1")
                                .answer("answer 1")
                                .build(),
                        Answer.builder()
                                .questionId("questionId 2")
                                .answer("answer X")
                                .build()))
                .build()));
    }
}