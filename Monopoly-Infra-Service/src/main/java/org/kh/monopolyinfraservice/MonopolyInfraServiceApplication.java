package org.kh.monopolyinfraservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MonopolyInfraServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonopolyInfraServiceApplication.class, args);
    }

}
