package com.geomapping.camera.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.geomapping.camera.model.CameraEntity;
import com.mongodb.client.model.geojson.Point;


public interface CameraRepository extends MongoRepository<CameraEntity, String> {
	
	  CameraEntity findById(ObjectId id);


	}