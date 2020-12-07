package com.cameraslewer.camera.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;

import com.cameraslewer.camera.model.LocationEntity;

@Configuration
public class MongoConfig {

	private static final String MONGO_DB_URL = "localhost";
	private static final String MONGO_DB_NAME = "embeded_db";

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void init() {
		mongoTemplate.indexOps(LocationEntity.class)
				.ensureIndex(new GeospatialIndex("location").typed(GeoSpatialIndexType.GEO_2DSPHERE));
	}

}