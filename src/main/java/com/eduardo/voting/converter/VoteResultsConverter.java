package com.eduardo.voting.converter;

import com.eduardo.voting.api.v1.web.request.VotingResultsRequest;
import com.eduardo.voting.api.v1.web.response.VotingResultsResponse;
import com.eduardo.voting.business.VotingResultsBO;
import com.eduardo.voting.entity.VotingResults;
import com.eduardo.voting.enums.VotingWinnerEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VoteResultsConverter {

    public static VotingResultsBO fromRequest(VotingResultsRequest votingResultsRequest) {
        BigDecimal totalVotes = votingResultsRequest.getTotalVotes();
        BigDecimal votesInFavor = votingResultsRequest.getVotesInFavor();

        return VotingResultsBO.builder()
                .sessionUUID(votingResultsRequest.getSessionUUID())
                .totalVotes(totalVotes)
                .votesInFavor(votesInFavor)
                .votingWinner(determineWinner(totalVotes, votesInFavor))
                .percentageWinner(calculateWinnerPercentage(totalVotes, votesInFavor))
                .build();
    }

    public static VotingResults fromBusinessObject(VotingResultsBO businessObject)  {
        return VotingResults.builder()
                .sessionUUID(businessObject.getSessionUUID())
                .winner(businessObject.getVotingWinner())
                .percentage(businessObject.getPercentageWinner())
                .build();
    }

    public static VotingResultsResponse fromResults(VotingResults votingResults) {
        return VotingResultsResponse.builder()
                .status(String.format(
                        getMessage(votingResults.getWinner()),
                        votingResults.getSessionUUID(),
                        votingResults.getWinner().value(),
                        votingResults.getPercentage()))
                .build();
    }

    private static BigDecimal calculateWinnerPercentage(BigDecimal totalVotes, BigDecimal votesInFavor) {
        return votesInFavor
                .multiply(new BigDecimal(100))
                .divide(totalVotes, 2, RoundingMode.HALF_EVEN);
    }

    private static VotingWinnerEnum determineWinner(BigDecimal totalVotes, BigDecimal votesInFavor) {
        int winnerCalculation = votesInFavor.compareTo(totalVotes.subtract(votesInFavor));
        switch (winnerCalculation){
            case 1: return VotingWinnerEnum.YES;
            case -1: return VotingWinnerEnum.NO;
            case 0: return VotingWinnerEnum.NONE;
            default: throw new RuntimeException(String.format(
                        "Failed to find winner. Voting calculation was %s, with %s votes in favor from %s"
                        , winnerCalculation
                        ,votesInFavor.toString()
                        ,totalVotes.toString()));
        }
    }

    private static String getMessage(VotingWinnerEnum winner) {
        if(winner.equals(VotingWinnerEnum.NONE)) return "Session %s voting has ended in a draw.";
        else return "Session %s voting has ended. The winner was %s with %s percent of the votes.";
    }
}
