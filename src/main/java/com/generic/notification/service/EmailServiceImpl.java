package com.generic.notification.service;

import java.io.FileNotFoundException;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mockito.internal.stubbing.answers.Returns;

import com.generic.notification.config.EmailConfig;
import com.generic.notification.requestbody.NotificationBody;
/**
 * 
 * @author </br>Bhumi Nandan</br>
 * Parameterized Constructor 
 * 
 */
public class EmailServiceImpl implements NotificationService{
	private String configFilePath;
	public EmailServiceImpl(String configFilePath) {
		this.configFilePath = configFilePath;
	}
	/**
	 * <br><p>This method takes config file and gets configurations in Session Object <p></br>
	 * <P> Finally sends email 
	 * Parameterized Constructor 
	 * @Parameters NotificationBody requestBody
	 * @throws FileNotFoundException
	 * @return status
	 */
	@Override
	public boolean notifyUser(NotificationBody requestBody) throws FileNotFoundException {
		boolean status =false;
		try {
		Session session = new EmailConfig().getConfigurations(configFilePath);
		MimeMessage msg = new MimeMessage(session);
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");
	      msg.setFrom(new InternetAddress("smtp.gmail.com", "Bhumi Nandan"));
	      msg.setSubject(requestBody.getSubject(), "UTF-8");
	      msg.setText(requestBody.getMailBody(), "UTF-8");
	      msg.setSentDate(new Date());
	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(requestBody.getEmailId(), false));
	      System.out.println("Message is ready");
	      Transport.send(msg);
	      status = true;
	      
		}catch(Exception e) {
			e.printStackTrace();
			status = false;
		}
	      System.out.println("EMail Sent Successfully!!");
		return status;
	}

}
