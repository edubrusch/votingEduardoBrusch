package com.eduardo.voting.api.v1.web.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VotingSessionRequest {
    @Builder.Default
    private final Long sessionDurationMinutes = 1L;
    @NotNull
    private String subject;
}
