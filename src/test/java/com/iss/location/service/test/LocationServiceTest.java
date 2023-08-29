package com.iss.location.service.test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.iss.location.entity.LocationEntity;
import com.iss.location.service.LocationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private LocationServiceImpl locationService;

	@Test
	@DisplayName("pass the test case when the location is available")
	public void getLocation() throws Exception {
		LocationEntity l = new LocationEntity("iss", 25544);

		Mockito.when(restTemplate.getForObject("https://api.wheretheiss.at/v1/satellites/25544.", LocationEntity.class))
				.thenReturn(l);

		LocationEntity loc = locationService.getLocation();
		Assertions.assertEquals(l, loc);
	}

	@Test
	@DisplayName("test case fails when values are different from what is expected")
	public void failWhenDifferentIdAndName() throws Exception {
		LocationEntity l = new LocationEntity("iss", 25544);

		Mockito.when(restTemplate.getForObject("https://api.wheretheiss.at/v1/satellites/25544.", LocationEntity.class))
				.thenReturn(l);

		LocationEntity loc = locationService.getLocation();
		Assertions.assertNotEquals("space", loc.getName());
		Assertions.assertNotEquals(1, loc.getId());
	}

	@Test
	@DisplayName("test should pass when response is null")
	public void passWhenNull() throws Exception {
		Mockito.when(restTemplate.getForObject("https://api.wheretheiss.at/v1/satellites/25544.", LocationEntity.class))
				.thenReturn(null);

		LocationEntity actual = locationService.getLocation();

		assertNull(actual);
	}

	@Test
	@DisplayName("test should passed when executed within specified duartion")
	public void timeout() throws InterruptedException {
		LocationEntity l = new LocationEntity("iss", 25544);

		Mockito.when(restTemplate.getForObject("https://api.wheretheiss.at/v1/satellites/25544.", LocationEntity.class))
				.thenThrow(new Exception()).thenReturn(l);

		assertTimeout(Duration.ofMillis(2000), () -> {
			locationService.getLocation();
		});
	}
}
