package com.tpaul.applicationtracker.repositories;

import com.tpaul.applicationtracker.entities.Answer;
import com.tpaul.applicationtracker.entities.Application;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    public void writeAndReadApplication() {
        Application input = Application.builder()
                .name("name")
                .answers(Collections.singletonList(Answer.builder()
                        .questionId("questionId")
                        .answer("answer")
                        .build()))
                .build();

        Application output = applicationRepository.save(input);

        assertNotNull(output.getId());
        assertEquals("name", output.getName());

        assertEquals(1, output.getAnswers().size());

        Answer answer = output.getAnswers().get(0);
        assertNotNull(answer.getId());
        assertEquals("questionId", answer.getQuestionId());
        assertEquals("answer", answer.getAnswer());
    }

    @Test
    public void findAll() {
        assertEquals(0, Lists.newArrayList(applicationRepository.findAll()).size());

        Application input = Application.builder()
                .name("name")
                .answers(Collections.singletonList(Answer.builder()
                        .questionId("questionId")
                        .answer("answer")
                        .build()))
                .build();

        applicationRepository.save(input);

        assertEquals(1, Lists.newArrayList(applicationRepository.findAll()).size());
    }
}