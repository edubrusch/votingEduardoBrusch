package com.eduardo.voting.converter;

import com.eduardo.voting.business.VoteBO;
import com.eduardo.voting.entity.Associate;

public class AssociateConverter {

    public static Associate fromBusinessObject(VoteBO voteBO) {
        return Associate.builder()
                .document(voteBO.getDocument())
                .sessionUUID(voteBO.getVotingSessionUuid())
                .build();
    }
}
