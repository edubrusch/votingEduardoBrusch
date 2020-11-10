package com.eduardo.voting.repository;

import com.eduardo.voting.entity.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingSessionRepository extends CrudRepository<Session, Long> {

    Optional<Session> findByVotingSessionUUID(String uuid);
}
