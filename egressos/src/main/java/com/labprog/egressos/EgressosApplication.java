package com.labprog.egressos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EgressosApplication {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
    }  

	public static void main(String[] args) {
		SpringApplication.run(EgressosApplication.class, args);
	}

}
