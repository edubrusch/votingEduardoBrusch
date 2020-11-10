package com.eduardo.voting.service.impl;

import com.eduardo.voting.api.v1.web.request.VotingResultsRequest;
import com.eduardo.voting.business.VoteBO;
import com.eduardo.voting.business.VotingResultsBO;
import com.eduardo.voting.business.VotingSessionBO;
import com.eduardo.voting.converter.AssociateConverter;
import com.eduardo.voting.converter.VoteConverter;
import com.eduardo.voting.converter.VoteResultsConverter;
import com.eduardo.voting.converter.VotingSessionConverter;
import com.eduardo.voting.entity.Associate;
import com.eduardo.voting.entity.Session;
import com.eduardo.voting.entity.Vote;
import com.eduardo.voting.entity.VotingResults;
import com.eduardo.voting.enums.VoteStatementEnum;
import com.eduardo.voting.repository.AssociateRepository;
import com.eduardo.voting.repository.VoteRepository;
import com.eduardo.voting.repository.VotingResultsRepository;
import com.eduardo.voting.repository.VotingSessionRepository;
import com.eduardo.voting.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotingSessionServiceImpl implements VotingSessionService {


    private final VotingSessionRepository votingSessionRepository;
    private final VotingResultsRepository votingResultsRepository;
    private final VoteRepository voteRepository;
    private final AssociateRepository associateRepository;

    public Session create(VotingSessionBO sessionData) {
        log.info("VotingSessionServiceImpl - Create Session");
        Session session = VotingSessionConverter.fromBusinessObject(sessionData);
        votingSessionRepository.save(session);
        log.info("VotingSessionServiceImpl - Session {} has been created", session.getVotingSessionUUID());

        return session;
    }

    public VotingResults retrieveVotingResults(String uuid) {
        log.info("VotingSessionServiceImpl - get voting results {}", uuid);
        sessionValidToProcess(uuid);
        BigDecimal totalVotes = new BigDecimal(voteRepository.countBySessionUUID(uuid));
        BigDecimal votesInFavor = new BigDecimal(
                voteRepository.countBySessionUUIDAndAndVoteStatement(uuid,VoteStatementEnum.YES));
        VotingResultsBO results = VoteResultsConverter.fromRequest(
                VotingResultsRequest.builder()
                .sessionUUID(uuid)
                .totalVotes(totalVotes)
                .votesInFavor(votesInFavor)
                .build());

        return votingResultsRepository.save(VoteResultsConverter.fromBusinessObject(results));
    }

    public Vote vote(VoteBO voteBusiness) {
        log.info("VotingSessionServiceImpl - proceed voting");
        Session votingSession = sessionValidToVote(voteBusiness);
        Associate associate = AssociateConverter.fromBusinessObject(voteBusiness);
        Vote vote = VoteConverter.fromBusinessObject(voteBusiness);
        votingSession.getParticipants().add(associate);
        votingSession.getVotes().add(vote);
        votingSessionRepository.save(votingSession);
        log.info("VotingSessionServiceImpl - voting complete");

        return vote;
    }

    private Session sessionValidToVote(VoteBO voteBusiness) {
        if(associateRepository.countBySessionUUIDAndAndDocument(
                voteBusiness.getVotingSessionUuid(), voteBusiness.getDocument()) != 0)
            throw new RuntimeException(String.format("User %s can only vote once in the session",
                            voteBusiness.getDocument(),
                            voteBusiness.getVotingSessionUuid()));
        Session session = findSession(voteBusiness.getVotingSessionUuid());
        if(sessionClosed(session)) throw new RuntimeException(String.format(
                "Voting on the session %s is closed",
                voteBusiness.getVotingSessionUuid()));
        return session;
    }

    private Session sessionValidToProcess(String uuid) {
        Session session = findSession(uuid);
        if(!sessionClosed(session))
            throw new RuntimeException(String.format("Session %s is still in progress", uuid));
        return session;
    }

    private boolean sessionClosed(Session session) {
        Instant sessionEnd = session.getCreated().toInstant()
                .plus(session.getTimeLimit(), ChronoUnit.MINUTES);

        return Instant.now().isAfter(sessionEnd);
    }

    private Session findSession(String uuid) {
        return votingSessionRepository.findByVotingSessionUUID(uuid).orElseThrow(
                () -> new RuntimeException(String.format("Session %s not found", uuid)));
    }
}