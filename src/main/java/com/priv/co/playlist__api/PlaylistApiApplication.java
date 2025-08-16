package com.priv.co.playlist__api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class PlaylistApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaylistApiApplication.class, args);
    }

}
