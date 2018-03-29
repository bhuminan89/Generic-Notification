package com.generic.notification.response;

import java.io.Serializable;
import java.util.HashMap;

import com.generic.notification.response.codes.ResponseCode;
import com.generic.notification.response.codes.ResponseMessage;

public class ApiResponse<T> implements Serializable {
	private static final long serialVersionUID = -3321644516058443285L;
	private int status;
	private String message;
	private T resource;
	
	public ApiResponse(T resource) {
		this.resource = resource;
		if (resource == null) {
			status = ResponseCode.FAILURE;
			message = ResponseMessage.EXC_MSG;
		} else if (resource instanceof HashMap) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = (HashMap<String, Object>) resource; 
		if (map.get("error") != null) {
			status = ResponseCode.INCORRECT_PARAMETER;
			message = map.get("error").toString();
		}else {
			status = ResponseCode.SUCCESS;
			message = ResponseMessage.SUCCESS_MSG;
		}
		}
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResource() {
		return resource;
	}

	public void setResource(T resource) {
		this.resource = resource;
	}
}
