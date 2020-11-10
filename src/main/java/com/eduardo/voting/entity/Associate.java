package com.eduardo.voting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "associate_voting")
@NoArgsConstructor
@AllArgsConstructor
public class Associate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "associate_id")
    private long AssociateId;
    private String document;
    @Column(name = "session_uuid")
    private String sessionUUID;
}
