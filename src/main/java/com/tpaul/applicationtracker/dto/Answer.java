package com.tpaul.applicationtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Answer {

    @NotEmpty
    private String id;
    @NotEmpty
    private String answer;

    com.tpaul.applicationtracker.entities.Answer toEntity() {
        return com.tpaul.applicationtracker.entities.Answer.builder()
                .questionId(this.id)
                .answer(this.answer)
                .build();
    }
}
