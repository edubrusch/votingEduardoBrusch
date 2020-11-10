package com.eduardo.voting.entity;

import com.eduardo.voting.enums.VoteStatementEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote_session")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vote_id")
    private Integer voteId;
    @Enumerated(EnumType.STRING)
    private VoteStatementEnum voteStatement;
    @Column(name = "session_uuid")
    private String sessionUUID;
}
