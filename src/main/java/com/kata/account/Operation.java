package com.kata.account;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Data
public class Operation {

    private long amount;

    private  Account account;

    private LocalDateTime date;

    private long newBalance;

    private long previousBalance;

    private OperationType operationType;

    public Operation(Account account, long amount, long previousBalance, long newBalance, OperationType operationType) {
        this.account = account;
        this.amount = amount;
        this.date = now();
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.operationType = operationType;
    }

}
