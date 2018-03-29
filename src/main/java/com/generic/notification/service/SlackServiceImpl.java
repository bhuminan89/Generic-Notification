package com.generic.notification.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import com.generic.notification.config.SlackConfig;
import com.generic.notification.requestbody.NotificationBody;

public class SlackServiceImpl implements NotificationService{
	String configFilePath ;
	public SlackServiceImpl(String configFilePath) {
		this.configFilePath = configFilePath;
	}
	@Override
	public boolean notifyUser(NotificationBody requestBody) {
		try {
		String USER_AGENT = "Mozilla/5.0";
		String url=new SlackConfig(configFilePath).getSlackWebHook();
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("content-type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("text", requestBody.getMailBody());
		
		// Send post request
		con.setDoOutput(true);
		OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
		wr.write(json.toString());
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
