package com.eduardo.voting.business;

import com.eduardo.voting.enums.VoteStatementEnum;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoteBO {
    private final String VotingSessionUuid;
    private final String document;
    private final VoteStatementEnum statement;
}
