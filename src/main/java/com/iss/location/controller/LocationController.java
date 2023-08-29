package com.iss.location.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iss.location.entity.LocationEntity;
import com.iss.location.service.LocationServiceImpl;

/**
 * This is a presentation layer which will fetch the data sent by service layer.
 * 
 * @author Aditya Dongre
 * @version 1.0
 */
@RestController
public class LocationController {

	@Autowired
	private LocationServiceImpl locationService;

	@GetMapping("/location")
	public LocationEntity getLocation() throws Exception {

		return locationService.getLocation();
	}
}
