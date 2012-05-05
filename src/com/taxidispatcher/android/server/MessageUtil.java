package com.taxidispatcher.android.server;

import groovy.xml.MarkupBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.taxidispatcher.data.JobXmlBuilder;
import com.taxidispatcher.model.Job;

public class MessageUtil {
	private final static String AUTH = "authentication";

	private static final String UPDATE_CLIENT_AUTH = "Update-Client-Auth";

	public static final String PARAM_REGISTRATION_ID = "registration_id";

	public static final String PARAM_DELAY_WHILE_IDLE = "delay_while_idle";

	public static final String PARAM_COLLAPSE_KEY = "collapse_key";

	private static final String UTF8 = "UTF-8";

	public static int sendMessage(String auth_token, String registrationId,
			String message) throws IOException {

		StringBuilder postDataBuilder = new StringBuilder();
		postDataBuilder.append(PARAM_REGISTRATION_ID).append("=")
				.append(registrationId);
		postDataBuilder.append("&").append(PARAM_COLLAPSE_KEY).append("=")
				.append("0");
		postDataBuilder.append("&").append("data.payload").append("=")
				.append(URLEncoder.encode(message, UTF8));

		byte[] postData = postDataBuilder.toString().getBytes(UTF8);

		// Hit the dm URL.

		URL url = new URL("https://android.clients.google.com/c2dm/send");
		HttpsURLConnection
				.setDefaultHostnameVerifier(new CustomizedHostnameVerifier());
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestProperty("Content-Length",
				Integer.toString(postData.length));
		conn.setRequestProperty("Authorization", "GoogleLogin auth="
				+ auth_token);

		OutputStream out = conn.getOutputStream();
		out.write(postData);
		out.close();

		int responseCode = conn.getResponseCode();
		return responseCode;
	}

	/**
	 * Creates Job xml to send to Android devices.
	 * @return
	 */
	public static String createMessageXml(Job job){
		JobXmlBuilder jxB = new JobXmlBuilder(job);
		String xml = (String)jxB.generateXml();		
		return xml;
	}
	
	/**
	 * Creates Job xml to send to Android devices.
	 * @return
	 */
	public static String createMessageJSON(Job job){
		
		return null;
	}
	
	public static int sendMessage(String registrationId, String message) throws IOException {
		return sendMessage(AuthenticationUtil.getToken(SecureStorage.USER, SecureStorage.PASSWORD), registrationId, message);
	}
	
	
	private static class CustomizedHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}


	public static String createTestMessageXml() {
		JobXmlBuilder jxB = new JobXmlBuilder();
		return jxB.generateTestXml();
	}
}