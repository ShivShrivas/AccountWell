package com.bsninfotech.accountwell.Helper;

public class Accounts_Helper {
    String SubCode,Name;

    public Accounts_Helper() {

    }

    public String getSubCode() {
        return SubCode;
    }

    public void setSubCode(String subCode) {
        SubCode = subCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Accounts_Helper(String subCode, String name) {
        SubCode = subCode;
        Name = name;
    }
}
