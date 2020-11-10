package com.eduardo.voting.repository;

import com.eduardo.voting.entity.VotingResults;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VotingResultsRepository extends CrudRepository<VotingResults, Long> {

    Optional<VotingResults> findBySessionUUID(String sessionUUID);
}
