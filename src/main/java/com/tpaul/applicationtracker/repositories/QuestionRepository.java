package com.tpaul.applicationtracker.repositories;

import com.tpaul.applicationtracker.entities.Question;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties
public class QuestionRepository {

    private List<Question> questions;
}
