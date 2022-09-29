package com.kata.account;

import jdk.nashorn.internal.objects.annotations.Getter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Account {
    private String idCompte ="MyBankAccout1";
    private  long balance = 0;
    private  List<Operation> operations = new ArrayList<>();


    public long deposit(long amount) throws Exception {
        if(amount<0){
            throw new Exception();
        }
        long previousBalance = balance;
        balance += amount;
        operations.add(new Operation(this, amount, previousBalance, balance, OperationType.DEPOSIT));
        return balance;
    }

    public long withdraw(long amount) throws Exception {
        if(amount>this.balance || amount<0){
            throw new Exception();
        }
       long previousBalance = this.balance;
       this.balance -= amount;
       this.operations.add(new Operation(this, amount, previousBalance, balance, OperationType.WITHDRAW));
       return balance;
    }

    public String showHistory() {

        String history= "";
        for(Operation operation : operations){
           history += "Compte: " + getIdCompte() + "    Type: "  + operation.getOperationType() +
            "    Montant: " + operation.getAmount() + "    Solde avant:  " + operation.getPreviousBalance() +
            "    Solde apres: " + operation.getNewBalance() + "    Date operation: " + operation.getDate() + "\n";
        }
        System.out.println(history);
        return history;
    }

    public  long showBalance() {
       return balance;
    }

    public static void main(String[] args) {

    }

}
