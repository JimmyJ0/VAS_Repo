package de.leuphana.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class CustomerApplication {
	
	@RequestMapping("/")
	  public String home() {
	    return "Customer Service";
	  }

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
