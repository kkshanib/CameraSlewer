package com.cameraslewer.camera.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cameraslewer.camera.vo.request.CameraRequest;
import com.cameraslewer.camera.vo.request.GetNearByLocationsRequest;
import com.cameraslewer.camera.vo.request.LocationRequest;
import com.cameraslewer.camera.vo.response.BaseResponse;
import com.cameraslewer.camera.vo.response.GetCameraListRespone;
import com.cameraslewer.camera.vo.response.GetLocationListResponse;
import com.cameraslewer.camera.vo.response.GetNearByLocationsResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/location")
public interface GeoMappingController {

	@GetMapping(value = "/listCamera")
	public GetCameraListRespone getAllCamera();

	@PostMapping(value = "/addCamera")
	public BaseResponse addCamera(@RequestBody CameraRequest cameraRequest);

	@GetMapping(value = "/listLocations")
	public GetLocationListResponse getAllLocations();

	@PostMapping(value = "/addLocation")
	public BaseResponse addLocation(@RequestBody LocationRequest locationRequest);

	@PostMapping(value = "/getNearByLocation")
	public GetNearByLocationsResponse getNearByLocation(
			@RequestBody GetNearByLocationsRequest getNearByLocationsRequest);

}
