package com.generic.notification.requestbody;

public class NotificationBody {
	private String emailId;
	private String subject;
	private String mailBody;
	private boolean slack;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public boolean isSlack() {
		return slack;
	}
	public void setSlack(boolean slack) {
		this.slack = slack;
	}
	
}
