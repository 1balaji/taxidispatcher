package com.taxidispatcher.android.server;

public class ServerConfiguration {
	
	private String registrationId;

	public ServerConfiguration(){

	}

	public ServerConfiguration(String registrationIdOfDevice){
		this.registrationId = registrationIdOfDevice;
	}

	public String getRegistrationId() {
		return registrationId;
	}

}
