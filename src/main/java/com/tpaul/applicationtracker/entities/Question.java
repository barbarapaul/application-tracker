package com.tpaul.applicationtracker.entities;

import lombok.Data;

import java.util.Set;

@Data
public class Question {

    private String id;

    private Set<String> answers;
}
