package com.iss.location.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.client.RestTemplate;

import com.iss.location.entity.LocationEntity;

/**
 * This is a service layer used to write the implementation logic behind
 * fetching the data from third party API with the help of RestTemplate.
 * 
 * @author Aditya Dongre
 * @version 1.0
 */
@Service
@CacheConfig(cacheNames = { "location" })
public class LocationServiceImpl implements LocationService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${location.url}")
	private String locationUrl;

	LocationEntity storeLastLocation;

	Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

	@Retryable(retryFor = InternalServerError.class, maxAttempts = 1, backoff = @Backoff(delay = 4000))
	@Cacheable
	public LocationEntity getLocation() throws Exception {
//		throw new Exception();
		Thread.sleep(1000);
		LocationEntity location = restTemplate.getForObject("https://api.wheretheiss.at/v1/satellites/25544.",
				LocationEntity.class);

		if (location != null) {
			storeLastLocation = location;
		}
		return location;
	}

	@Recover
	public LocationEntity recover() throws InterruptedException {
		if (storeLastLocation != null) {
			return storeLastLocation;
		}
		LocationEntity l = new LocationEntity();
		return l;
	}
}
