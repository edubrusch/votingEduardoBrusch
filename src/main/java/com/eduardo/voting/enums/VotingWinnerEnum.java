package com.eduardo.voting.enums;

public enum VotingWinnerEnum {
    YES("SIM"),
    NO("Não"),
    NONE("Empate");

    private final String statement;

    VotingWinnerEnum(String statement) {
        this. statement = statement;
    }

    public Object value() {
        return statement;
    }
}
