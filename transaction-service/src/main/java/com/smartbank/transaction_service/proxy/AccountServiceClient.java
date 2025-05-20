package com.smartbank.transaction_service.proxy;

import com.smartbank.transaction_service.dto.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountServiceClient {
    @GetMapping("/account/{id}")
    ResponseEntity<Account> getAccountById(@PathVariable Integer id);

    @PutMapping("/account/{id}")
    ResponseEntity<Account> updateAccountById(@PathVariable int id, @RequestBody Account account);
}
