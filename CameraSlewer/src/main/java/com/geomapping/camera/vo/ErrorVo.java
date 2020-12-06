package com.geomapping.camera.vo;

import java.io.Serializable;

public class ErrorVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -877439498837214671L;

	private String errorCode;
	
	private String errorDescription;

	public ErrorVo(String errorCode, String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
