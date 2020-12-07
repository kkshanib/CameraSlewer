package com.cameraslewer.camera.service;

import com.cameraslewer.camera.vo.request.CameraRequest;
import com.cameraslewer.camera.vo.request.GetNearByLocationsRequest;
import com.cameraslewer.camera.vo.request.LocationRequest;
import com.cameraslewer.camera.vo.response.BaseResponse;
import com.cameraslewer.camera.vo.response.GetCameraListRespone;
import com.cameraslewer.camera.vo.response.GetLocationListResponse;
import com.cameraslewer.camera.vo.response.GetNearByLocationsResponse;

public interface GeomappingService {

	public GetCameraListRespone getAllCamera();

	public BaseResponse addCamera(CameraRequest cameraRequest);

	public GetLocationListResponse getAllLocations();

	public BaseResponse addLocation(LocationRequest locationRequest);

	public GetNearByLocationsResponse getNearByLocation(GetNearByLocationsRequest getNearByLocationsRequest);

}
