package com.eduardo.voting.repository;

import com.eduardo.voting.entity.Associate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AssociateRepository extends CrudRepository<Associate, Long> {

    Optional<Associate> findBySessionUUID(String sessionUUID);
    Long countBySessionUUID(String sessionUUID);
    Long countBySessionUUIDAndAndDocument(String sessionUUID, String Document);
}
