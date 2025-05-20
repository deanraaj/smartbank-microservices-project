package com.smartbank.transaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer id;

    private Integer userId;

    private String accountType; // e.g., SAVINGS, CURRENT
    private Double balance;

    private Boolean isActive;
}