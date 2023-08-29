package com.iss.location.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.iss.location.controller.LocationController;
import com.iss.location.entity.LocationEntity;
import com.iss.location.service.LocationServiceImpl;

@SpringBootTest
public class LocationControllerTest {

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private LocationServiceImpl locationService;

	@InjectMocks
	private LocationController locationController;

	@Test
	@DisplayName("when location method called the api should return the result")
	public void getLocation() throws Exception {

		LocationEntity l = new LocationEntity("iss", 25544);

		Mockito.when(locationController.getLocation()).thenReturn(l);

		LocationEntity loc = locationController.getLocation();

		assertEquals(l, loc);
	}

	@Test
	@DisplayName("test case fails when values are different from what is expected")
	public void failWhenDifferentIdAndName() throws Exception {
		LocationEntity l = new LocationEntity("iss", 25544);

		Mockito.when(locationController.getLocation()).thenReturn(l);

		LocationEntity loc = locationController.getLocation();
		Assertions.assertNotEquals("space", loc.getName());
		Assertions.assertNotEquals(1, loc.getId());
	}

	@Test
	@DisplayName("test should pass when response is null")
	public void passWhenNull() throws Exception {
		Mockito.when(locationController.getLocation()).thenReturn(null);

		LocationEntity actual = locationController.getLocation();

		assertNull(actual);
	}

	@Test
	@DisplayName("test should passed when executed within specified duartion")
	public void timeout() throws Exception {
		LocationEntity l = new LocationEntity("iss", 25544);

		Mockito.when(locationController.getLocation()).thenReturn(l);

		assertTimeout(Duration.ofMillis(2000), () -> {
			locationController.getLocation();
		});
	}

}
