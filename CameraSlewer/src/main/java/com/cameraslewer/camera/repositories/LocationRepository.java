package com.cameraslewer.camera.repositories;

import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cameraslewer.camera.model.LocationEntity;

public interface LocationRepository extends MongoRepository<LocationEntity, String> {

	List<LocationEntity> findByLocationNear(Point p);

}
