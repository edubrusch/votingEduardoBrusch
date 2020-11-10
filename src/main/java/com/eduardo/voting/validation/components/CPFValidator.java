package com.eduardo.voting.validation.components;

import com.eduardo.voting.validation.client.CPFStatus;
import com.eduardo.voting.validation.client.CPFValidationClient;
import com.eduardo.voting.validation.converter.CPFStatusConverter;
import com.eduardo.voting.validation.enums.CPFStatusEnum;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class CPFValidator implements ConstraintValidator<CPFConstraint, String> {

    private final CPFValidationClient cpfClient;

    @Override
    public void initialize(CPFConstraint cpf) {}

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        CPFStatus status;
        try{
            status = cpfClient.userCPF(cpf);
        } catch (FeignException feign) {
            throw new RuntimeException(String.format("CPF %s is considered invalid. Please verify CPF data", cpf));
        }
        return CPFStatusConverter.fromCPFStatus(status)
                .equals(CPFStatusEnum.ABLE_TO_VOTE);
    }
}
