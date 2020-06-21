package com.algaworks.osworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OsworksApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(OsworksApiApplication.class, args);
	}

}

@RestController
class HelloController {

	@GetMapping("/")
	String hello(){
		return "Hello World";
	}
}
