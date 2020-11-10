package com.eduardo.voting.service.impl;

import com.eduardo.voting.business.VotingSessionBO;
import com.eduardo.voting.entity.Session;
import com.eduardo.voting.repository.AssociateRepository;
import com.eduardo.voting.repository.VoteRepository;
import com.eduardo.voting.repository.VotingResultsRepository;
import com.eduardo.voting.repository.VotingSessionRepository;
import com.eduardo.voting.service.VotingSessionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VotingSessionServiceImplTest {

    @Mock
    private VotingSessionRepository votingSessionRepository;
    @Mock
    private VotingSessionBO votingSessionBO;
    @Mock
    private Session session;

    @InjectMocks
    private VotingSessionServiceImpl votingSessionService;

    @Test
    @DisplayName("Should create Session and return it")
    void shouldSucceedCreateSession() {
        when(votingSessionRepository.save(any(Session.class))).thenReturn(session);
        Session sessionTest = votingSessionService.create(votingSessionBO);
        assertNotNull(sessionTest);
    }
}