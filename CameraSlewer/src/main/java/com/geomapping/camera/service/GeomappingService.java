package com.geomapping.camera.service;

import com.geomapping.camera.vo.request.CameraRequest;
import com.geomapping.camera.vo.request.GetNearByLocationsRequest;
import com.geomapping.camera.vo.request.LocationRequest;
import com.geomapping.camera.vo.response.BaseResponse;
import com.geomapping.camera.vo.response.GetCameraListRespone;
import com.geomapping.camera.vo.response.GetLocationListResponse;
import com.geomapping.camera.vo.response.GetNearByLocationsResponse;


public interface GeomappingService {
	
	 public GetCameraListRespone getAllCamera();	 

	 public BaseResponse addCamera(CameraRequest cameraRequest);
	 
	 public GetLocationListResponse getAllLocations(); 

	 public BaseResponse addLocation(LocationRequest locationRequest);
	 
	 public GetNearByLocationsResponse getNearByLocation(GetNearByLocationsRequest getNearByLocationsRequest);

}
