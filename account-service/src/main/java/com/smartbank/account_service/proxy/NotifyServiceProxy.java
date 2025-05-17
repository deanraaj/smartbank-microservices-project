package com.smartbank.account_service.proxy;

import com.smartbank.account_service.dto.NotificationRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("NOTIFY-SERVICE")
public interface NotifyServiceProxy {
    @PostMapping("/notify")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDto notificationRequestDto);
}
