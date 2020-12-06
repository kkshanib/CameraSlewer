package com.geomapping.camera.vo.request;

import java.io.Serializable;

import com.geomapping.camera.vo.Camera;

public class GetNearByLocationsRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3657838398549933930L;
	private Camera camera;

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	

}
