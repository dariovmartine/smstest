package com.directv.smstest;

public class AQSmsPostMessageRequest  {

	private String requestID;
	private String sourceID;
	protected String identification;
	private String keyword;
	private String serialOrIdentification;
	private String channel;
	private String shortNumber;
	private String phoneNumber;
	private String operator;
	private String trash;
	private String SMSdatetime;
	
	private String flow;
	
	private String customerId;

	public AQSmsPostMessageRequest() {
		super();
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getSourceID() {
		return sourceID;
	}

	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSerialOrIdentification() {
		return serialOrIdentification;
	}

	public void setSerialOrIdentification(String serialOrIdentification) {
		this.serialOrIdentification = serialOrIdentification;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getShortNumber() {
		return shortNumber;
	}

	public void setShortNumber(String shortNumber) {
		this.shortNumber = shortNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getTrash() {
		return trash;
	}

	public void setTrash(String trash) {
		this.trash = trash;
	}
	
	public String getSMSdatetime() {
		return SMSdatetime;
	}
	
	public void setSMSdatetime(String sMSdatetime) {
		SMSdatetime = sMSdatetime;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "AQSmsPostMessageRequest [requestID=" + requestID + ", sourceID=" + sourceID + ", identification="
				+ identification + ", keyword=" + keyword + ", serialOrIdentification=" + serialOrIdentification
				+ ", channel=" + channel + ", shortNumber=" + shortNumber + ", phoneNumber=" + phoneNumber
				+ ", operator=" + operator + ", trash=" + trash + ", SMSdatetime=" + SMSdatetime + ", flow=" + flow
				+ ", customerId=" + customerId + "]";
	}



}
