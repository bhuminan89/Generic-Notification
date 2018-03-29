package com.generic.notification.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class EmailConfig {

	/**
	 * <br>
	 * This method reads configuration properties file for sending email and
	 * authenciates email server using email and password provided in the file </br>
	 * 
	 * @param configFilePath
	 * @return Session
	 * @throws FileNotFoundException
	 */
	public Session getConfigurations(String configFilePath) throws FileNotFoundException {

		Properties prop = new Properties();
		FileInputStream input = null;
		try {
			input = new FileInputStream(configFilePath);
			prop.load(input);
			String fromEmail = prop.getProperty("from.email");
			String password = prop.getProperty("password");

			Properties props = new Properties();
			props.load(input);
			props.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
			props.put("mail.smtp.port", prop.getProperty("mail.smtp.port"));
			props.put("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
			props.put("mail.smtp.starttls.enable", prop.getProperty("mail.smtp.starttls.enable"));

			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Session session = Session.getInstance(props, auth);
			return session;

		} catch (IOException ex) {
			throw new FileNotFoundException();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
