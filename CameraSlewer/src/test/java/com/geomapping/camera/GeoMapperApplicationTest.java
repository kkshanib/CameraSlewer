package com.geomapping.camera;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.geomapping.camera.enums.CameraType;
import com.geomapping.camera.enums.ResponseStatus;
import com.geomapping.camera.service.GeomappingService;
import com.geomapping.camera.vo.Camera;
import com.geomapping.camera.vo.Location;
import com.geomapping.camera.vo.request.CameraRequest;
import com.geomapping.camera.vo.request.GetNearByLocationsRequest;
import com.geomapping.camera.vo.request.LocationRequest;
import com.geomapping.camera.vo.response.BaseResponse;
import com.geomapping.camera.vo.response.GetNearByLocationsResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration
public class GeoMapperApplicationTest {
		
	@Autowired
	private GeomappingService geoMappingService;
	
	@Test
    public void addCameraTest() {
        CameraRequest cameraRequest = new CameraRequest();
        Camera camera = new Camera(); 
        camera.setName("Al Markiyya Cam1");
        camera.setCameraType(CameraType.FRONT);
        camera.setLatitude("25.3388");
        camera.setLongitude("51.4992");
        camera.setAzimuth("25.00");
        cameraRequest.setCamera(camera);
        BaseResponse response = geoMappingService.addCamera(cameraRequest);
        assertNotNull(response);
        assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));
        
    }
	
	@Test
    public void addLocationTest1() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setLocation(createLocation("Al Bayt Stadium", "25.6523", "51.4878"));      
        BaseResponse response = geoMappingService.addLocation(locationRequest);
        assertNotNull(response);
        assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));       
    }
	
	@Test
    public void addLocationTest2() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setLocation(createLocation("Lusail Stadium", "25.4213", "51.4904"));
        BaseResponse response = geoMappingService.addLocation(locationRequest);
        assertNotNull(response);
        assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));       
    }
	
	@Test
    public void getNearByLocationTest() {
        GetNearByLocationsRequest locationRequest = new  GetNearByLocationsRequest();
        Camera camera = new Camera(); 
        camera.setName("Al Markiyya Cam1");
        camera.setCameraType(CameraType.FRONT);
        camera.setLatitude("25.3388");
        camera.setLongitude("51.4992");
        camera.setAzimuth("25.00");
        locationRequest.setCamera(camera);
        GetNearByLocationsResponse response = geoMappingService.getNearByLocation(locationRequest);
        assertNotNull(response);
        assertNotNull(response.getLocations());
        assertTrue(response.getStatus().equals(ResponseStatus.SUCCESS));       
    }
	
	private Location createLocation(String name, String latitude, String longitude) {
		Location location = new Location(); 
        location.setLocationName(name);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
		
	}
}