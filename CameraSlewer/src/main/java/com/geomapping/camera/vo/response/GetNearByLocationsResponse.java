package com.geomapping.camera.vo.response;

import java.util.List;

import com.geomapping.camera.vo.Camera;
import com.geomapping.camera.vo.Location;

public class GetNearByLocationsResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8480071176486878672L;
	
	private List<Location> locations;
	
	private Camera camera;

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
}
