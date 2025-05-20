package com.smartbank.transaction_service.proxy;

import com.smartbank.transaction_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id);
}
