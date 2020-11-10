package com.eduardo.voting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "session_uuid", unique = true)
    private String votingSessionUUID;
    private Long timeLimit;
    private Date created;
    private String subject;

    @Builder.Default
    @OneToMany(mappedBy = "sessionUUID", cascade = CascadeType.ALL)
    private final List<Vote> votes= new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "sessionUUID", cascade = CascadeType.ALL)
    private final List<Associate> participants= new ArrayList<>();

    @OneToOne
    private VotingResults votingResults;
}
