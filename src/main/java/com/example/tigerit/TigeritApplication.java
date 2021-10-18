package com.example.tigerit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class TigeritApplication {

	public static void main(String[] args) {
		SpringApplication.run(TigeritApplication.class, args);
	}

}
