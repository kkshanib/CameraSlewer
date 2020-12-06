package com.geomapping.camera.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.geomapping.camera.api.GeoMappingController;
import com.geomapping.camera.service.GeomappingService;
import com.geomapping.camera.vo.request.CameraRequest;
import com.geomapping.camera.vo.request.GetNearByLocationsRequest;
import com.geomapping.camera.vo.request.LocationRequest;
import com.geomapping.camera.vo.response.BaseResponse;
import com.geomapping.camera.vo.response.GetCameraListRespone;
import com.geomapping.camera.vo.response.GetLocationListResponse;
import com.geomapping.camera.vo.response.GetNearByLocationsResponse;

@Component
public class GeoMappingControllerImpl implements GeoMappingController {

	@Autowired
	@Qualifier("daoService")
	private GeomappingService daoService;

	@Override
	public GetCameraListRespone getAllCamera() {
		return daoService.getAllCamera();
	}

	@Override
	public BaseResponse addCamera(CameraRequest cameraRequest) {
		return daoService.addCamera(cameraRequest);
	}

	@Override
	public GetLocationListResponse getAllLocations() {
		return daoService.getAllLocations();
	}

	@Override
	public BaseResponse addLocation(LocationRequest locationRequest) {
		return daoService.addLocation(locationRequest);
	}

	@Override
	public GetNearByLocationsResponse getNearByLocation(GetNearByLocationsRequest getNearByLocationsRequest) {
		return daoService.getNearByLocation(getNearByLocationsRequest);
	}

}
