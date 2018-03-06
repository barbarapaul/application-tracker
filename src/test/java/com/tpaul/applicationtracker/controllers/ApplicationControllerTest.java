package com.tpaul.applicationtracker.controllers;

import com.tpaul.applicationtracker.entities.Answer;
import com.tpaul.applicationtracker.entities.Application;
import com.tpaul.applicationtracker.exceptions.ApplicationNotQualifiedException;
import com.tpaul.applicationtracker.services.ApplicationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class ApplicationControllerTest {

    private final ApplicationService applicationServiceMock = mock(ApplicationService.class);


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ApplicationController applicationController;
    private MockMvc mvc;

    @Before
    public void setup() {
        applicationController.setApplicationService(applicationServiceMock);
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    public void submitInvalidApplication() throws Exception {
        mvc.perform(
                post("/api/v1/applications")
                        .content("{\"name\": null}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void submitQualifiedApplication() throws Exception {
        mvc.perform(
                post("/api/v1/applications")
                        .content("{\n" +
                                "  \"name\": \"Application Name\",\n" +
                                "  \"answers\": [\n" +
                                "    {\n" +
                                "      \"id\": \"id01\",\n" +
                                "      \"answer\": \"answer 1\"\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"id\": \"id02\",\n" +
                                "      \"answer\": \"answer 2\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(applicationServiceMock, times(1)).submitApplication(Application.builder()
                .name("Application Name")
                .answers(Arrays.asList(Answer.builder()
                                .questionId("id01")
                                .answer("answer 1")
                                .build(),
                        Answer.builder()
                                .questionId("id02")
                                .answer("answer 2")
                                .build()))
                .build());
    }

    @Test
    public void submitNotQualifiedApplication() throws Exception {
        doThrow(ApplicationNotQualifiedException.class).when(applicationServiceMock).submitApplication(any(Application.class));

        mvc.perform(
                post("/api/v1/applications")
                        .content("{\n" +
                                "  \"name\": \"Application Name\",\n" +
                                "  \"answers\": [\n" +
                                "    {\n" +
                                "      \"id\": \"id01\",\n" +
                                "      \"answer\": \"answer 1\"\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"id\": \"id02\",\n" +
                                "      \"answer\": \"answer 2\"\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());

        verify(applicationServiceMock, times(1)).submitApplication(Application.builder()
                .name("Application Name")
                .answers(Arrays.asList(Answer.builder()
                                .questionId("id01")
                                .answer("answer 1")
                                .build(),
                        Answer.builder()
                                .questionId("id02")
                                .answer("answer 2")
                                .build()))
                .build());
    }

    @Test
    public void getApplications() throws Exception {
        when(applicationServiceMock.getAllApplications()).thenReturn(Arrays.asList(
                Application.builder()
                        .name("name 1")
                        .answers(Arrays.asList(Answer.builder()
                                        .questionId("questionId 1")
                                        .answer("answer 1")
                                        .build(),
                                Answer.builder()
                                        .questionId("questionId 2")
                                        .answer("answer 2")
                                        .build()))
                        .build(),
                Application.builder()
                        .name("name 2")
                        .answers(Arrays.asList(Answer.builder()
                                        .questionId("questionId 1")
                                        .answer("answer 1")
                                        .build(),
                                Answer.builder()
                                        .questionId("questionId 2")
                                        .answer("answer 2")
                                        .build()))
                        .build()));

        mvc.perform(
                get("/api/v1/applications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(equalTo("name 1"))))
                .andExpect(jsonPath("$[0].answers", hasSize(2)))
                .andExpect(jsonPath("$[0].answers[0].id", is(equalTo("questionId 1"))))
                .andExpect(jsonPath("$[0].answers[0].answer", is(equalTo("answer 1"))));
    }
}