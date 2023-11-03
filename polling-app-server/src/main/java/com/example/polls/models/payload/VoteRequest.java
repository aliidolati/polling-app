package com.example.polls.models.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoteRequest {
    @NotNull
    private Long choiceId ;
}
