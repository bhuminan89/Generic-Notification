package com.generic.notification.service;

import java.io.FileNotFoundException;

import com.generic.notification.requestbody.NotificationBody;

public interface NotificationService {
	public boolean notifyUser(NotificationBody requestBody) throws FileNotFoundException;
}
