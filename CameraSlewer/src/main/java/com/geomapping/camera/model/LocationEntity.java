package com.geomapping.camera.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "locations")
public class LocationEntity {
  private ObjectId id;
  private String name;
  
  @GeoSpatialIndexed(name  = "location", type = GeoSpatialIndexType.GEO_2DSPHERE)
  private GeoJsonPoint location;

  public LocationEntity(final String name, final GeoJsonPoint location) {
    this.name = name;
    this.location = location;
  }

    
	public LocationEntity() {

   }


	public String getId() {
		return id.toHexString();
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public GeoJsonPoint getLocation() {
		return location;
	}
	
	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	  
  
  
}
