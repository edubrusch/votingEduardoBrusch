package com.eduardo.voting.business;

import com.eduardo.voting.entity.Associate;
import com.eduardo.voting.entity.Vote;
import com.eduardo.voting.entity.VotingResults;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter
public class VotingSessionBO {
    private final String votingSessionUUID;
    private final Long timeLimit;
    private final Date created;
    private final String subject;
    @Builder.Default
    private final List<Vote> votes = new ArrayList<>();
    @Builder.Default
    private final List<Associate> participants = new ArrayList<>();
    private final VotingResults votingResults;
}
