package com.eduardo.voting.repository;

import com.eduardo.voting.entity.Vote;
import com.eduardo.voting.enums.VoteStatementEnum;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {

    Long countBySessionUUID(String sessionUUID);
    Long countBySessionUUIDAndAndVoteStatement(String sessionUUID, VoteStatementEnum voteStatement);
}
