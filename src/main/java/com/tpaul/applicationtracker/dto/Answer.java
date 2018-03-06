package com.tpaul.applicationtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
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

    static Answer fromEntity(com.tpaul.applicationtracker.entities.Answer answer) {
        return Answer.builder()
                .id(answer.getQuestionId())
                .answer(answer.getAnswer())
                .build();
    }
}
