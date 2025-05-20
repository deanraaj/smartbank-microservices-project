package com.smartbank.transaction_service.controller;

import com.smartbank.transaction_service.dto.TransactionDto;
import com.smartbank.transaction_service.entity.Transactions;
import com.smartbank.transaction_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionDto transactionDto) {
        Transactions transaction = transactionService.createTransaction(transactionDto);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }
}
