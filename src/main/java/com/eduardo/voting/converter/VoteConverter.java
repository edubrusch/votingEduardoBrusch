package com.eduardo.voting.converter;

import com.eduardo.voting.api.v1.web.request.VoteRequest;
import com.eduardo.voting.api.v1.web.response.VoteResponse;
import com.eduardo.voting.business.VoteBO;
import com.eduardo.voting.entity.Vote;
import com.eduardo.voting.enums.VoteStatementEnum;

public class VoteConverter {

    public static VoteBO fromRequest(VoteRequest voteRequest, String sessionUuid) {
        return VoteBO.builder()
                .VotingSessionUuid(sessionUuid)
                .document(voteRequest.getDocument())
                .statement(VoteStatementEnum.fromString(voteRequest.getStatement()))
                .build();
    }

    public static Vote fromBusinessObject(VoteBO voteBO) {
        return Vote.builder()
                .voteStatement(voteBO.getStatement())
                .sessionUUID(voteBO.getVotingSessionUuid()).build();
    }

    public static VoteResponse fromVote(Vote vote) {
        return VoteResponse.builder()
                .status(String.format(
                        "Your vote has been successfully submitted on session %s"
                        , vote.getSessionUUID()))
                .build();
    }
}
