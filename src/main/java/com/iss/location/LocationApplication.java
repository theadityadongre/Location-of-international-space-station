package com.iss.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

/**
 * This Location service returns current latitude, longitude, velocity and many
 * other properties of International Space Station.
 * 
 * @author Aditya Dongre
 * @version 1.0
 */
@SpringBootApplication
@EnableRetry
@EnableCaching
public class LocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationApplication.class, args);
	}

}
