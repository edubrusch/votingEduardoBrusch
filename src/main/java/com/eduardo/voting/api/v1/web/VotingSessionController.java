package com.eduardo.voting.api.v1.web;

import com.eduardo.voting.api.v1.web.request.VoteRequest;
import com.eduardo.voting.api.v1.web.request.VotingSessionRequest;
import com.eduardo.voting.api.v1.web.response.VoteResponse;
import com.eduardo.voting.api.v1.web.response.VotingResultsResponse;
import com.eduardo.voting.api.v1.web.response.VotingSessionResponse;
import com.eduardo.voting.business.VotingSessionBO;
import com.eduardo.voting.converter.VoteConverter;
import com.eduardo.voting.converter.VoteResultsConverter;
import com.eduardo.voting.converter.VotingSessionConverter;
import com.eduardo.voting.entity.Session;
import com.eduardo.voting.entity.Vote;
import com.eduardo.voting.entity.VotingResults;
import com.eduardo.voting.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingSessionController {

    private final VotingSessionService votingSessionService;

    @PostMapping(path = "/session", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VotingSessionResponse> startVotingSession(@Valid @RequestBody VotingSessionRequest votingSessionRequest) {
        log.info("VotingController - Create voting session");
        VotingSessionBO votingSessionBO = VotingSessionConverter.fromRequest(votingSessionRequest);
        Session session = votingSessionService.create(votingSessionBO);
        VotingSessionResponse response = VotingSessionConverter.fromSession(session);
        log.info("VotingController - Voting session created");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/vote/{uuid}")
    public ResponseEntity<VoteResponse> vote(@PathVariable String uuid, @Valid @RequestBody VoteRequest voteRequest) {
        log.info("VotingController - Requesting vote subject");
        Vote vote = votingSessionService.vote(VoteConverter.fromRequest(voteRequest, uuid));
        VoteResponse response = VoteConverter.fromVote(vote);
        log.info("VotingController - Vote subject processed");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/session/{uuid}/results")
    public ResponseEntity<VotingResultsResponse> sessionResults(@PathVariable String uuid) {
        log.info("VotingController - Requesting Voting results");
        VotingResults results = votingSessionService.retrieveVotingResults(uuid);
        VotingResultsResponse response = VoteResultsConverter.fromResults(results);
        log.info("VotingController - Voting results processed");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
