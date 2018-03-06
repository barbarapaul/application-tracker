package com.tpaul.applicationtracker.repositories;

import com.tpaul.applicationtracker.entities.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void getQuestions() {
        assertEquals(2, questionRepository.getQuestions().size());

        Question question1 = questionRepository.getQuestions().get(0);
        assertEquals("id01", question1.getId());
        assertEquals(new HashSet<>(Arrays.asList("answer 1", "answer 2")), question1.getAnswers());

        Question question2 = questionRepository.getQuestions().get(1);
        assertEquals("id02", question2.getId());
        assertEquals(new HashSet<>(Arrays.asList("answer 2", "answer 3")), question2.getAnswers());
    }
}