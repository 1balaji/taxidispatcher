package com.taxidispatcher.android.server;


import java.io.IOException;

import com.taxidispatcher.constant.AndroidConfigUtil;

public class SendMessageToDevice {

	private String message;
	private String deviceId;
	public SendMessageToDevice(){

	}

	public SendMessageToDevice(String message, String deviceId){
		this.message = message;
		this.deviceId = deviceId;
	}

	private int sendMessage() throws IOException{
		return MessageUtil.sendMessage( AndroidConfigUtil.AUTHENTICATION_TOKEN,deviceId, message );
	}


	public static void main(String[] args) throws IOException {
		// "Message to your device." is the message we will send to the Android app
		int responseCode = MessageUtil.sendMessage(
				AndroidConfigUtil.AUTHENTICATION_TOKEN,
				AndroidConfigUtil.REGISTRATION_ID, "Message to your device.");
		System.out.println(responseCode);
	}
}
