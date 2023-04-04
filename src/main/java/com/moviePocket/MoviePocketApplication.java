package com.moviePocket;
//https://www.youtube.com/watch?v=L9oWG6aj_U8

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MoviePocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviePocketApplication.class, args);
	}

}
