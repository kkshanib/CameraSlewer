package com.geomapping.camera.vo.response;

import java.io.Serializable;
import java.util.List;

import com.geomapping.camera.vo.Camera;

public class GetCameraListRespone extends BaseResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3100135880275829647L;

	private List<Camera> camerraList;
	
	public List<Camera> getCamerraList() {
		return camerraList;
	}

	public void setCamerraList(List<Camera> camerraList) {
		this.camerraList = camerraList;
	}

	
}
