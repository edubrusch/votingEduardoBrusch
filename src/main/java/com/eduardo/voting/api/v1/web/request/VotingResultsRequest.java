package com.eduardo.voting.api.v1.web.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class VotingResultsRequest {
    private final String sessionUUID;
    private final BigDecimal totalVotes;
    private final BigDecimal votesInFavor;
}
