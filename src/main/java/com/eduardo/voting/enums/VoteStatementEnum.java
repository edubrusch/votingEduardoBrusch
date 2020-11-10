package com.eduardo.voting.enums;

public enum VoteStatementEnum {
    YES("Sim"),
    NO("Não");

    private final String statement;

    VoteStatementEnum(String statement) {
        this. statement = statement;
    }

    public String value() {
        return statement;
    }

    public static VoteStatementEnum fromString(String statement) {

        switch(statement.toUpperCase()){
            case "NÃO":
            case "NAO":
            case "NO":
                return NO;
            case "SIM":
            case "YES":
                return YES;
            default:
                throw new RuntimeException(
                        String.format(
                                "Opção de voto %s inválido. Votos devem ser \"sim\" ou \"Não\".",
                                statement)
                );
        }
    }
}
