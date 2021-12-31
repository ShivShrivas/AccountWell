package com.bsninfotech.accountwell.Helper;

public class Stock_Helper {
    String Name,QtyPerBag,NoOfBag,TotalBag;
    public Stock_Helper(){}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQtyPerBag() {
        return QtyPerBag;
    }

    public void setQtyPerBag(String qtyPerBag) {
        QtyPerBag = qtyPerBag;
    }

    public String getNoOfBag() {
        return NoOfBag;
    }

    public void setNoOfBag(String noOfBag) {
        NoOfBag = noOfBag;
    }

    public String getTotalBag() {
        return TotalBag;
    }

    public void setTotalBag(String totalBag) {
        TotalBag = totalBag;
    }

    public Stock_Helper(String name, String qtyPerBag, String noOfBag, String totalBag) {
        Name = name;
        QtyPerBag = qtyPerBag;
        NoOfBag = noOfBag;
        TotalBag = totalBag;
    }
}
