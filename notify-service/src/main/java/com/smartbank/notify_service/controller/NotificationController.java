package com.smartbank.notify_service.controller;

import com.smartbank.notify_service.dto.NotificationRequestDto;
import com.smartbank.notify_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/notify")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDto notificationRequestDto) {
        String response = notificationService.sendNotification(notificationRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
