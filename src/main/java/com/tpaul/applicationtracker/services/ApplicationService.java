package com.tpaul.applicationtracker.services;

import com.tpaul.applicationtracker.entities.Application;
import com.tpaul.applicationtracker.exceptions.ApplicationNotQualifiedException;
import com.tpaul.applicationtracker.repositories.ApplicationRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final QualificationService qualificationService;
    private final ApplicationRepository applicationRepository;

    public ApplicationService(@NonNull QualificationService qualificationService,
                              @NonNull ApplicationRepository applicationRepository) {
        this.qualificationService = qualificationService;
        this.applicationRepository = applicationRepository;
    }

    public void submitApplication(@NonNull Application application) throws ApplicationNotQualifiedException {
        if (qualificationService.applicationQualifies(application)) {
            applicationRepository.save(application);
        } else {
            throw new ApplicationNotQualifiedException();
        }
    }

}
