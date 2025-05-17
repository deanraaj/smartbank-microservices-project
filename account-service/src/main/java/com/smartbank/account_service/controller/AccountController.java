package com.smartbank.account_service.controller;

import com.smartbank.account_service.dto.AccountDto;
import com.smartbank.account_service.dto.UserDto;
import com.smartbank.account_service.entity.Account;
import com.smartbank.account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // create account
    @PostMapping
    public ResponseEntity<Account> createNewAccount(@RequestBody AccountDto accountDto) {
        Account account = accountService.createAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    // get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    // get all accounts by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<HashMap<UserDto, List<Account>>> getByUserId(@PathVariable int userId) {
        HashMap<UserDto, List<Account>> userWithAccounts = accountService.getByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userWithAccounts);
    }

    // get account by id
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        Account account = accountService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    // update the account
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccountById(@PathVariable int id, @RequestBody Account account) {
        Account updatedAccount = accountService.updateAccountBy(id, account);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
        String response = accountService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
