package com.tpaul.applicationtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Data
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
}
