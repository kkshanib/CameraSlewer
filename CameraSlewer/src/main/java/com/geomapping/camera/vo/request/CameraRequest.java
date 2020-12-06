package com.geomapping.camera.vo.request;

import java.io.Serializable;

import com.geomapping.camera.vo.Camera;


public class CameraRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7070710444826023359L;
	
	private Camera camera;

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	@Override
	public String toString() {
		return "CameraBuilderRequest [camera=" + camera + ", getCamera()=" + getCamera() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
