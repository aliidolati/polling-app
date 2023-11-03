package com.example.polls.models.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Instant joinedAt;
    private Long pollCount;
    private Long voteCount;
}
