package com.nagarro.bfsitraining.watchstore.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
	private int errorCode;
	private String errorMessage;
	private LocalDateTime errorTimestamp;
	
	
	public ErrorResponse() {
	}


	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ErrorResponse(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorTimestamp = LocalDateTime.now();
	}


	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}


	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}


	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}


	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	/**
	 * @return the errorTimestamp
	 */
	public LocalDateTime getErrorTimestamp() {
		return errorTimestamp;
	}


	/**
	 * @param errorTimestamp the errorTimestamp to set
	 */
	public void setErrorTimestamp(LocalDateTime errorTimestamp) {
		this.errorTimestamp = errorTimestamp;
	}

	
	

}
