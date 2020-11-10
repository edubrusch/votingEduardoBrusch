package com.eduardo.voting.business;

import com.eduardo.voting.enums.VotingWinnerEnum;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class VotingResultsBO {
    private final String sessionUUID;
    private final BigDecimal totalVotes;
    private final BigDecimal votesInFavor;
    private final BigDecimal percentageWinner;
    private final VotingWinnerEnum votingWinner;
}
