package com.tpaul.applicationtracker.controllers;

import com.tpaul.applicationtracker.dto.Application;
import com.tpaul.applicationtracker.exceptions.ApplicationNotQualifiedException;
import com.tpaul.applicationtracker.services.ApplicationService;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {

    @Setter
    private ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public void submit(@RequestBody @Valid Application application) throws ApplicationNotQualifiedException {
        applicationService.submitApplication(application.toEntity());
    }

    @GetMapping
    public List<Application> getApplications() {
        return applicationService.getAllApplications()
                .stream()
                .map(Application::fromEntity)
                .collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Application is not qualified")
    @ExceptionHandler(ApplicationNotQualifiedException.class)
    public void applicationNotQualified() {
    }
}
