package com.eduardo.voting.validation.converter;

import com.eduardo.voting.validation.client.CPFStatus;
import com.eduardo.voting.validation.enums.CPFStatusEnum;


public class CPFStatusConverter {

    public static CPFStatusEnum fromCPFStatus(CPFStatus cpfStatus) {
        switch (cpfStatus.getStatus()){
            case "ABLE_TO_VOTE":
                return CPFStatusEnum.ABLE_TO_VOTE;
            case "UNABLE_TO_VOTE":
                return CPFStatusEnum.UNABLE_TO_VOTE;
            default:
                throw new RuntimeException(
                        String.format(
                                "Error parsing Status from CPF Validation client: %s"
                                , cpfStatus.getStatus()
                        ));
        }
    }
}
