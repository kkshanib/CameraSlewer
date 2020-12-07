package com.cameraslewer.camera.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cameraslewer.camera.model.CameraEntity;

public interface CameraRepository extends MongoRepository<CameraEntity, String> {

	CameraEntity findById(ObjectId id);

}