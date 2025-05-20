package com.smartbank.transaction_service.service;

import com.smartbank.transaction_service.dto.Account;
import com.smartbank.transaction_service.dto.NotificationRequestDto;
import com.smartbank.transaction_service.dto.TransactionDto;
import com.smartbank.transaction_service.dto.UserDto;
import com.smartbank.transaction_service.entity.TransactionType;
import com.smartbank.transaction_service.entity.Transactions;
import com.smartbank.transaction_service.proxy.AccountServiceClient;
import com.smartbank.transaction_service.proxy.NotificationServiceClient;
import com.smartbank.transaction_service.proxy.UserServiceClient;
import com.smartbank.transaction_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private NotificationServiceClient notifyClient;

    public Transactions createTransaction(TransactionDto transactionDto) {
        // Create Transactions object from given transactionDto
        Transactions transactions = new Transactions();
        transactions.setTransactionType(TransactionType.valueOf(transactionDto.getType()));
        transactions.setAccountId(transactionDto.getAccountId());
        transactions.setAmount(transactionDto.getAmount());
//        transactions.setCreatedAt();

        // fetch the account by accountId
        Account account = accountServiceClient.getAccountById(transactions.getAccountId()).getBody();

        // check if the balance is available or not
        if(transactions.getTransactionType().equals(TransactionType.DEBIT) && account.getBalance() < transactions.getAmount()) {
            throw new RuntimeException("Insufficient funds");
        }

        // Fetch the User details by account.getUserId
        UserDto user = userServiceClient.getUserById(account.getUserId()).getBody();

        Transactions savedTransaction = transactionRepository.save(transactions);

        double accountBalance = transactions.getTransactionType().equals(TransactionType.DEBIT) ? account.getBalance() - transactions.getAmount():
                account.getBalance() + transactions.getAmount();
        account.setBalance(accountBalance);

        // Update the account balance
        accountServiceClient.updateAccountById(transactions.getAccountId(), account).getBody();

        // Send email notification regarding Transaction
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
        notificationRequestDto.setTo(user.getEmail());
        notificationRequestDto.setMessage(String.valueOf(transactions.getTransactionType()) + " Transaction is completed successfully \n" +
                "Available balance is " + account.getBalance());

        notifyClient.sendNotification(notificationRequestDto);

        // return the transaction
        return transactions;

    }
}
