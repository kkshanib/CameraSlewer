package com.geomapping.camera.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cameras")
public class CameraEntity {
	@Id
	private ObjectId id;
	private String name;
	private String cameraType;
	private double azimuth;
	private GeoJsonPoint location;
	
	public CameraEntity(String name, String cameraType, double azimuth, GeoJsonPoint location) {
		this.name = name;
		this.cameraType = cameraType;
		this.azimuth = azimuth;
		this.location = location;
	}
	
	public CameraEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id.toHexString();
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCameraType() {
		return cameraType;
	}
	public void setCameraType(String cameraType) {
		this.cameraType = cameraType;
	}
	public double getAzimuth() {
		return azimuth;
	}
	public void setAzimuth(double azimuth) {
		this.azimuth = azimuth;
	}
	public GeoJsonPoint getLocation() {
		return location;
	}
	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}
	
	

}
