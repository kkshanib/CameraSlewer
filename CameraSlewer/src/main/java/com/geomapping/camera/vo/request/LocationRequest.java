package com.geomapping.camera.vo.request;

import java.io.Serializable;

import com.geomapping.camera.vo.Location;

public class LocationRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8616856214350555203L;
	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "LocationRequest [location=" + location + ", getLocation()=" + getLocation() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
