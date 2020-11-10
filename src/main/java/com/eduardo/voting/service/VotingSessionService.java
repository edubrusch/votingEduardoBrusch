package com.eduardo.voting.service;

import com.eduardo.voting.business.VoteBO;
import com.eduardo.voting.business.VotingSessionBO;
import com.eduardo.voting.entity.Session;
import com.eduardo.voting.entity.Vote;
import com.eduardo.voting.entity.VotingResults;
import org.springframework.stereotype.Service;

@Service
public interface VotingSessionService {

    Session create(VotingSessionBO sessionData);

    VotingResults retrieveVotingResults(String uuid);

    Vote vote(VoteBO voteBusiness);
}
