package com.cameraslewer.camera.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cameraslewer.camera.api.GeoMappingController;
import com.cameraslewer.camera.service.GeomappingService;
import com.cameraslewer.camera.vo.request.CameraRequest;
import com.cameraslewer.camera.vo.request.GetNearByLocationsRequest;
import com.cameraslewer.camera.vo.request.LocationRequest;
import com.cameraslewer.camera.vo.response.BaseResponse;
import com.cameraslewer.camera.vo.response.GetCameraListRespone;
import com.cameraslewer.camera.vo.response.GetLocationListResponse;
import com.cameraslewer.camera.vo.response.GetNearByLocationsResponse;

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
