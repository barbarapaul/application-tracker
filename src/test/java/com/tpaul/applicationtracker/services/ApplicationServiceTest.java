package com.tpaul.applicationtracker.services;

import com.tpaul.applicationtracker.entities.Application;
import com.tpaul.applicationtracker.exceptions.ApplicationNotQualifiedException;
import com.tpaul.applicationtracker.repositories.ApplicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceTest {

    @Mock
    private QualificationService qualificationServiceMock;

    @Mock
    private ApplicationRepository applicationRepositoryMock;

    @InjectMocks
    private ApplicationService applicationService;

    @Test(expected = ApplicationNotQualifiedException.class)
    public void submitApplicationNotQualified() throws Exception {
        Application application = Application.builder()
                .name("name")
                .answers(Collections.emptyList())
                .build();

        when(qualificationServiceMock.applicationQualifies(application)).thenReturn(false);

        try {
            applicationService.submitApplication(application);
        } finally {
            verifyZeroInteractions(applicationRepositoryMock);
        }
    }

    @Test
    public void submitApplicationQualified() throws Exception {
        Application application = Application.builder()
                .name("name")
                .answers(Collections.emptyList())
                .build();

        when(qualificationServiceMock.applicationQualifies(application)).thenReturn(true);

        applicationService.submitApplication(application);
        verify(applicationRepositoryMock, times(1)).save(application);
    }
}