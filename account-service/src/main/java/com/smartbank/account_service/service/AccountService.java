package com.smartbank.account_service.service;

import com.smartbank.account_service.dto.AccountDto;
import com.smartbank.account_service.dto.NotificationRequestDto;
import com.smartbank.account_service.dto.UserDto;
import com.smartbank.account_service.entity.Account;
import com.smartbank.account_service.proxy.NotifyServiceProxy;
import com.smartbank.account_service.proxy.UserServiceClient;
import com.smartbank.account_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private NotifyServiceProxy notifyServiceProxy;

    // Create account
    public Account createAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountType(accountDto.getAccountType());

        if(account.getAccountType().equals("SAVING")) {
            account.setBalance(1000d);
        } else {
            account.setBalance(0d);
        }
        account.setUserId(accountDto.getUserId());
        Account savedAccount = accountRepository.save(account);
        UserDto userDto = userServiceClient.getUserById(account.getUserId()).getBody();

        NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
        notificationRequestDto.setTo(userDto.getEmail());
        notificationRequestDto.setMessage("Congratulations, Your account is created successfully in Bank. \n Your Account Details : \n"
                + "Account Name : " + userDto.getName() + "\n Account Number: " + savedAccount.getId());


        //
        notifyServiceProxy.sendNotification(notificationRequestDto);

        return savedAccount;
    }

    // Get all accounts
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        if(accounts.isEmpty()) throw new RuntimeException("Exception..");
        return accounts;
    }

    public HashMap<UserDto, List<Account>> getByUserId(int userId) {
        UserDto user = userServiceClient.getUserById(userId).getBody();
        List<Account> accountList = accountRepository.findByUserId(userId);
        HashMap<UserDto, List<Account>> map = new HashMap<>();
        map.put(user, accountList);
        if(accountList.isEmpty()) throw new RuntimeException("Exception..");
        return map;
    }


    public Account getById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isEmpty()) throw new RuntimeException("Exception..");
        return optionalAccount.get();
    }


    public Account updateAccountBy(int id, Account account) {
        Account existingAccount = getById(id);
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBalance(account.getBalance());
        existingAccount.setUserId(account.getUserId());
        existingAccount.setIsActive(account.getIsActive());
        return accountRepository.save(existingAccount);
    }

    public String deleteById(int id) {
        Account existingAccount = getById(id);
        accountRepository.delete(existingAccount);
        return "Account with id " + id + " has been deleted!";
    }
}
