package com.eduardo.voting.api.v1.web.request;

import com.eduardo.voting.validation.components.CPFConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequest {
    @CPFConstraint
    private String document;
    private String statement;
}
