package com.eduardo.voting.converter;

import com.eduardo.voting.api.v1.web.request.VotingSessionRequest;
import com.eduardo.voting.api.v1.web.response.VotingSessionResponse;
import com.eduardo.voting.business.VotingSessionBO;
import com.eduardo.voting.entity.Session;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public final class VotingSessionConverter {

    public static Session fromBusinessObject(VotingSessionBO votingSessionBO) {
        return Session.builder()
                .created(votingSessionBO.getCreated())
                .participants(new ArrayList<>())
                .subject(votingSessionBO.getSubject())
                .timeLimit(votingSessionBO.getTimeLimit())
                .votes(new ArrayList<>())
                .votingSessionUUID(votingSessionBO.getVotingSessionUUID())
                .build();
    }

    public static VotingSessionBO fromRequest(VotingSessionRequest votingSessionRequest) {
        return VotingSessionBO.builder()
        .timeLimit(votingSessionRequest.getSessionDurationMinutes())
        .created(Date.from(Instant.now()))
        .subject(votingSessionRequest.getSubject())
        .build();
    }

    public static VotingSessionResponse fromSession(Session session) {
        return VotingSessionResponse.builder().status("Voting Session created. Subject: "+session.getSubject())
                .sessionIdentifier(session.getVotingSessionUUID())
                .build();
    }
}
