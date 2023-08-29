package com.iss.location.service;

import com.iss.location.entity.LocationEntity;

/**
 * This is a Location Service Interface, here we declared all the necessary
 * methods.
 * 
 * @author Aditya Dongre
 * @version 1.0
 *
 */
public interface LocationService {

	public LocationEntity getLocation() throws Exception;

}
