package com.geomapping.camera.vo.response;

import java.io.Serializable;
import java.util.List;

import com.geomapping.camera.vo.Location;

public class GetLocationListResponse  extends BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 900441959677991822L;
	
	private List<Location> locations;

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "GetLocationListResponse [locations=" + locations + ", getLocations()=" + getLocations()
				+ ", getStatus()=" + getStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
