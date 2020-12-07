package com.cameraslewer.camera.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cameraslewer.camera.constants.ErrorConstants;
import com.cameraslewer.camera.vo.ErrorVo;
import com.cameraslewer.camera.vo.request.CameraRequest;
import com.cameraslewer.camera.vo.request.LocationRequest;

@Component("requestValidator")
public class RequestValidator {

	public List<ErrorVo> validateAddCameraRequest(CameraRequest cameraRequest) {
		List<ErrorVo> errorList = new ArrayList<ErrorVo>();
		if (cameraRequest != null && cameraRequest.getCamera() != null) {

		} else {
			errorList.add(new ErrorVo(ErrorConstants.REQUEST_DATA_MISSING_CODE, ErrorConstants.REQUEST_DATA_MISSING));
		}

		return errorList;
	}

	public List<ErrorVo> validateAddLocationRequest(LocationRequest locationRequest) {
		List<ErrorVo> errorList = new ArrayList<ErrorVo>();
		if (locationRequest != null && locationRequest.getLocation() != null) {

		} else {
			errorList.add(new ErrorVo(ErrorConstants.REQUEST_DATA_MISSING_CODE, ErrorConstants.REQUEST_DATA_MISSING));
		}

		return errorList;
	}

}
