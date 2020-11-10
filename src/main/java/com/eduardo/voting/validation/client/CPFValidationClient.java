package com.eduardo.voting.validation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CPFValidation", url = "https://user-info.herokuapp.com")
public interface CPFValidationClient {

    @GetMapping("/users/{cpf}")
    CPFStatus userCPF(@PathVariable String cpf);
}
