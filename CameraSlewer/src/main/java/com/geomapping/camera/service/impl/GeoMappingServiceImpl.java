package com.geomapping.camera.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import com.geomapping.camera.enums.CameraType;
import com.geomapping.camera.enums.ResponseStatus;
import com.geomapping.camera.model.CameraEntity;
import com.geomapping.camera.model.LocationEntity;
import com.geomapping.camera.repositories.CameraRepository;
import com.geomapping.camera.repositories.LocationRepository;
import com.geomapping.camera.service.GeomappingService;
import com.geomapping.camera.validators.RequestValidator;
import com.geomapping.camera.vo.Camera;
import com.geomapping.camera.vo.ErrorVo;
import com.geomapping.camera.vo.Location;
import com.geomapping.camera.vo.request.CameraRequest;
import com.geomapping.camera.vo.request.GetNearByLocationsRequest;
import com.geomapping.camera.vo.request.LocationRequest;
import com.geomapping.camera.vo.response.BaseResponse;
import com.geomapping.camera.vo.response.GetCameraListRespone;
import com.geomapping.camera.vo.response.GetLocationListResponse;
import com.geomapping.camera.vo.response.GetNearByLocationsResponse;



@Service("daoService")
public class GeoMappingServiceImpl implements GeomappingService {
	
	@Autowired
	private CameraRepository cameraRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	 
	@Autowired
	@Qualifier("requestValidator")
	private RequestValidator requestValidator;

	@Override
	public GetCameraListRespone getAllCamera() {
		GetCameraListRespone cameraListRespone = new GetCameraListRespone();
		List<CameraEntity> cameraList = cameraRepository.findAll();
		if(!cameraList.isEmpty()) {
			cameraListRespone.setCamerraList(cameraListResponse(cameraList));

		}
		return cameraListRespone;
	}
	
	@Override
	public BaseResponse addCamera(CameraRequest cameraRequest) {
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatus.FAILURE);
		List<ErrorVo> errorList = requestValidator.validateAddCameraRequest(cameraRequest);
		if(errorList.isEmpty()) {
			try {
				Camera camera = cameraRequest.getCamera();
				CameraEntity cameraEntity = getCameraEntity(camera);
				cameraRepository.save(cameraEntity);
				response.setStatus(ResponseStatus.SUCCESS);
				System.out.println("Success fully saved");
			} catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}			
		}
		return response;
	}

	@Override
	public GetLocationListResponse getAllLocations() {
		GetLocationListResponse locationListResponse = new GetLocationListResponse();
		List<LocationEntity> locationList = locationRepository.findAll();
		if(locationList != null && !locationList.isEmpty()) {
			locationListResponse.setLocations(createLocationResponse(locationList));
			locationListResponse.setStatus(ResponseStatus.SUCCESS);
		}
		return locationListResponse;
	}

	@Override
	public BaseResponse addLocation(LocationRequest locationRequest) {
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatus.FAILURE);
		List<ErrorVo> errorList = requestValidator.validateAddLocationRequest(locationRequest);
		if(errorList.isEmpty()) {
			try {
				Location location = locationRequest.getLocation();
				LocationEntity locationEntity = getLocationEntity(location);
				locationRepository.save(locationEntity);
				response.setStatus(ResponseStatus.SUCCESS);
				System.out.println("Successfully saved location entity");
			} catch(Exception e) {
				System.out.println(e.getMessage());
				
			}			
		}
		return response;
	}



	@Override
	public GetNearByLocationsResponse getNearByLocation(GetNearByLocationsRequest request) {
		GetNearByLocationsResponse response = new GetNearByLocationsResponse();
		Camera camera = request.getCamera();
		List<LocationEntity> locationList = locationRepository.findByLocationNear(new Point(Double.valueOf(camera.getLongitude()), 
				Double.valueOf(camera.getLatitude())));
		if(locationList != null && !locationList.isEmpty()) {
			response.setLocations(createLocationResponse(locationList));
			response.setStatus(ResponseStatus.SUCCESS);
		}else {
			response.setStatus(ResponseStatus.FAILURE);
		}
		return response;
	}
	
	
	private CameraEntity getCameraEntity(Camera cameraRequest) {
		CameraEntity cameraEntity = new CameraEntity();
		cameraEntity.setName(cameraRequest.getName());
		cameraEntity.setCameraType(cameraRequest.getCameraType().toString());
		final GeoJsonPoint locationPoint = new GeoJsonPoint(
		        Double.valueOf(cameraRequest.getLongitude()),
		        Double.valueOf(cameraRequest.getLatitude()));
		cameraEntity.setLocation(locationPoint);
		cameraEntity.setAzimuth( Double.valueOf(cameraRequest.getAzimuth()));
		
		return cameraEntity;
	}
	

	private List<Camera> cameraListResponse(List<CameraEntity> cameraList) {
		return cameraList.stream()
			                .map(object -> {
			                	Camera camera = new Camera();
			                	camera.setId(object.getId());
			                	camera.setName(object.getName());
			                	camera.setAzimuth(Double.toString(object.getAzimuth()));
			                	camera.setCameraType(CameraType.valueOf(object.getCameraType()));
			                	camera.setLongitude(setLongitudeFrmMap(object.getLocation()));
			                	camera.setLatitude(setLatitudeFrmMap(object.getLocation()));
			                    return camera;
			                })
			                .collect(Collectors.toList());	
	}
	
	private String setLongitudeFrmMap(GeoJsonPoint geoJsonPoint) {
		return Double.toString(geoJsonPoint.getCoordinates().get(0));
	}
	
	private String setLatitudeFrmMap(GeoJsonPoint geoJsonPoint) {
		return Double.toString(geoJsonPoint.getCoordinates().get(1));
	}
	
	private List<Location>  createLocationResponse(List<LocationEntity> locationList) {
		return locationList.stream()
                .map(object -> {
                	Location location = new Location();
                	location.setId(object.getId());
                	location.setLocationName(object.getName());
                	location.setLongitude(setLongitudeFrmMap(object.getLocation()));
                	location.setLatitude(setLatitudeFrmMap(object.getLocation()));
                    return location;
                })
                .collect(Collectors.toList());
		
		
		
	}
	
	private LocationEntity getLocationEntity(Location location) {
		LocationEntity locationEntity = new LocationEntity();
		locationEntity.setName(location.getLocationName());
		final GeoJsonPoint locationPoint = new GeoJsonPoint(
		        Double.valueOf(location.getLongitude()),
		        Double.valueOf(location.getLatitude()));
		locationEntity.setLocation(locationPoint);
	
		
		return locationEntity;
	}

}
