package com.nagarro.bfsitraining.watchstore.dto;

import java.time.LocalDateTime;

public class ApiResponse {
	
	private int responseCode;
	private String responseMessage;
	private LocalDateTime responseTimestamp;
	
	public ApiResponse(int responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.responseTimestamp = LocalDateTime.now();
	}

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * @return the responseTimestamp
	 */
	public LocalDateTime getResponseTimestamp() {
		return responseTimestamp;
	}

	/**
	 * @param responseTimestamp the responseTimestamp to set
	 */
	public void setResponseTimestamp(LocalDateTime responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}
	
	
	

}
