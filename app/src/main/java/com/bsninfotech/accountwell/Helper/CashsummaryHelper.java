package com.bsninfotech.accountwell.Helper;

public class CashsummaryHelper {
    String Name,Notation;
    Double Balance;
    public CashsummaryHelper(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public String getNotation() {
        return Notation;
    }

    public void setNotation(String notation) {
        Notation = notation;
    }

    public CashsummaryHelper(String name, Double balance, String notation) {
        Name = name;
        Balance = balance;
        Notation = notation;
    }
}
