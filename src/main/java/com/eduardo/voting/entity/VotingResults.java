package com.eduardo.voting.entity;

import com.eduardo.voting.enums.VotingWinnerEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voting_results")
public class VotingResults {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "result_id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    private VotingWinnerEnum winner;
    private BigDecimal percentage;
    @JoinColumn(name = "session_uuid")
    @Column(name = "session_uuid")
    private String sessionUUID;
}
