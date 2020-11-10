package com.eduardo.voting.api.v1.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteResponse {
    private String status;
}
