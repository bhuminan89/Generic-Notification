package com.generic.notification.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SlackConfig {
	String configFilePath;

	public SlackConfig(String configFilePath) throws IOException {
		this.configFilePath = configFilePath;
	}

	/**
	 * This method reads slack webhook from the config file path
	 * 
	 * @return slack webhook
	 * @throws IOException
	 */
	public String getSlackWebHook() throws IOException {
		Properties prop = new Properties();
		FileInputStream input = null;
		input = new FileInputStream(configFilePath);
		prop.load(input);
		return prop.getProperty("slack.webhook");
	}
}
