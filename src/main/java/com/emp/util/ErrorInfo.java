package com.emp.util;

import java.time.LocalDate;

public class ErrorInfo {

	private String errorMsg;
	
	private int errorCode;
	
	private LocalDate timeStamp;

	public ErrorInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorInfo(String errorMsg, int errorCode, LocalDate timeStamp) {
		super();
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
		this.timeStamp = timeStamp;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
