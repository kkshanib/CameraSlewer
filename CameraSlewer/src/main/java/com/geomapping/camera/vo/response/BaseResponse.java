package com.geomapping.camera.vo.response;

import java.io.Serializable;

import com.geomapping.camera.enums.ResponseStatus;
import com.geomapping.camera.vo.ErrorVo;

public class BaseResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2623043230844137080L;
	
	private ResponseStatus status;
	
	private ErrorVo error;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public ErrorVo getError() {
		return error;
	}

	public void setError(ErrorVo error) {
		this.error = error;
	}
	
	
	
	

}
