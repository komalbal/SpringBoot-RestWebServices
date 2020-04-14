package com.example.restWebService.userPackage.exception;

import java.util.Date;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionResponse {

	private Date timeStamp;
	private String errorMessage;
	private String details;
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public ExceptionResponse(Date timeStamp, String errorMessage, String details) {
		super();
		this.timeStamp = timeStamp;
		this.errorMessage = errorMessage;
		this.details = details;
	}
}
