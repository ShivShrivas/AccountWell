package com.bsninfotech.accountwell.Helper;

public class Ledger_Helper {
    String TransId,RefVoucherType,rownum,Date,NDate,V_Type,CvNo,SubCode,Name,CreditAmt,DebitAmt,Balance,DRCR;

    public String getTransId() {
        return TransId;
    }

    public void setTransId(String transId) {
        TransId = transId;
    }

    public String getRefVoucherType() {
        return RefVoucherType;
    }

    public void setRefVoucherType(String refVoucherType) {
        RefVoucherType = refVoucherType;
    }

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNDate() {
        return NDate;
    }

    public void setNDate(String NDate) {
        this.NDate = NDate;
    }

    public String getV_Type() {
        return V_Type;
    }

    public void setV_Type(String v_Type) {
        V_Type = v_Type;
    }

    public String getCvNo() {
        return CvNo;
    }

    public void setCvNo(String cvNo) {
        CvNo = cvNo;
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

    public String getCreditAmt() {
        return CreditAmt;
    }

    public void setCreditAmt(String creditAmt) {
        CreditAmt = creditAmt;
    }

    public String getDebitAmt() {
        return DebitAmt;
    }

    public void setDebitAmt(String debitAmt) {
        DebitAmt = debitAmt;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getDRCR() {
        return DRCR;
    }

    public void setDRCR(String DRCR) {
        this.DRCR = DRCR;
    }

    public Ledger_Helper(String transId, String refVoucherType, String rownum, String date, String NDate, String v_Type, String cvNo, String subCode, String name, String creditAmt, String debitAmt, String balance, String DRCR) {
        TransId = transId;
        RefVoucherType = refVoucherType;
        this.rownum = rownum;
        Date = date;
        this.NDate = NDate;
        V_Type = v_Type;
        CvNo = cvNo;
        SubCode = subCode;
        Name = name;
        CreditAmt = creditAmt;
        DebitAmt = debitAmt;
        Balance = balance;
        this.DRCR = DRCR;
    }
}
