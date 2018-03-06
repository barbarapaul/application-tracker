package com.tpaul.applicationtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @NotEmpty
    private String name;
    private List<Answer> answers;

    public com.tpaul.applicationtracker.entities.Application toEntity() {
        return com.tpaul.applicationtracker.entities.Application.builder()
                .name(this.name)
                .answers(this.answers.stream()
                        .map(Answer::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Application fromEntity(@NonNull com.tpaul.applicationtracker.entities.Application application) {
        return Application.builder()
                .name(application.getName())
                .answers(application.getAnswers().stream()
                        .map(Answer::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
