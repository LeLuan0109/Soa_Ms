package com.project.app.auth.client;

import com.project.app.auth.dto.EmailNotificationEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "notification-auth-service", url = "${service.notification}")
public interface NotificationServiceClient {
//
//  @PostMapping("/system/sendEmail")
//  void sendEmail(@RequestBody EmailNotificationEvent eventNTFDto);

}
