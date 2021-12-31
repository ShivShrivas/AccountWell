package com.bsninfotech.accountwell.Helper;

public class Transactions_Helper {
    String V_Type,PVNo,PVDate,Name,Amount;
    public Transactions_Helper(){}
    public Transactions_Helper(String v_Type, String PVNo, String PVDate, String name, String amount) {
        V_Type = v_Type;
        this.PVNo = PVNo;
        this.PVDate = PVDate;
        Name = name;
        Amount = amount;
    }

    public String getV_Type() {
        return V_Type;
    }

    public void setV_Type(String v_Type) {
        V_Type = v_Type;
    }

    public String getPVNo() {
        return PVNo;
    }

    public void setPVNo(String PVNo) {
        this.PVNo = PVNo;
    }

    public String getPVDate() {
        return PVDate;
    }

    public void setPVDate(String PVDate) {
        this.PVDate = PVDate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
