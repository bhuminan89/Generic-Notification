package com.generic.notification.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.generic.notification.requestbody.NotificationBody;
import com.generic.notification.response.ApiResponse;
import com.generic.notification.service.EmailServiceImpl;
import com.generic.notification.service.SlackServiceImpl;

/**
 * 
 * @author Bhumi Nandan </br>
 *         NotificationController class will act as starting end point for
 *         sending notification through email and slack. Rest end point starts
 *         with http://hostname:port no/notify/users Takes json object as a
 *         request parameters Example of input requestBody will look like
 * 
 *         { "emailId":"bhuminan89@gmail.com", "subject":"test",
 *         "mailBody":"testing email", "slack" :1 } if slack variable is set 1,
 *         the system will notify user through email as well as slack else will
 *         send only email notification.</br>
 *         
 *         Change configFilePath as available path
 */
@RestController
public class NotificationController {
	@PostMapping("/notify/users")
	public synchronized ApiResponse<Map<Object, String>> sendNotification(@RequestBody List<NotificationBody> requestBody) {
		Map<Object, String> responseMap = new HashMap<Object, String>();
		Queue<NotificationBody> messageQueue = new LinkedList<NotificationBody>();
		try {
		messageQueue.addAll(requestBody);
		String configFilePath = "/home/admin1/target-use-case/config.properties";
		
			for(NotificationBody queue:messageQueue) {	
			if (!queue.isSlack()) {
				new EmailServiceImpl(configFilePath).notifyUser(queue);
			} else {
				new EmailServiceImpl(configFilePath).notifyUser(queue);
				new SlackServiceImpl(configFilePath).notifyUser(queue);
				responseMap.put("Message", "Success");
			}
			messageQueue.remove(queue);
			}
		} catch (FileNotFoundException e) {
			responseMap.put("error", "Some Exception Occured!");
			e.printStackTrace();

		}
		return new ApiResponse<Map<Object,String>>(responseMap);
	}
}
