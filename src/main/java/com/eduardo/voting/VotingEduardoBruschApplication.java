package com.eduardo.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VotingEduardoBruschApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingEduardoBruschApplication.class, args);
	}

}
