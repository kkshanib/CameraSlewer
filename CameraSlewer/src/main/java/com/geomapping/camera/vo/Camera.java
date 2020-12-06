package com.geomapping.camera.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.geomapping.camera.enums.CameraType;

@JsonIgnoreProperties
public class Camera  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3405949094569000291L;
	private String id;
	private String name;
	private CameraType cameraType;
	private String azimuth;
	private String latitude;
	private String longitude;
	
	public  String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CameraType getCameraType() {
		return cameraType;
	}
	public void setCameraType(CameraType cameraType) {
		this.cameraType = cameraType;
	}
	public String getAzimuth() {
		return azimuth;
	}
	public void setAzimuth(String azimuth) {
		this.azimuth = azimuth;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

	
	
	

}
