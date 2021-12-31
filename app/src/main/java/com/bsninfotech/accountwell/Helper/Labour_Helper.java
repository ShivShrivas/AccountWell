package com.bsninfotech.accountwell.Helper;

public class Labour_Helper {
    String date;
    String amount;
    String number;
    String Qualification;
    String DoctorProfilePic;
    String AppoinedTime;
    String AppointmentDate;
    String Purpose;
    String MRAppointmentId;
    String MapLocation;
    String ProductName;
    String Rating;
    String StartOn;
    String DStatus;



    public Labour_Helper(String date, String amount, String number){

      this.date=date;
      this.amount=amount;
      this.number=number;


    }



    public String getdate() {
        return date;
    }
    public void setdate(String date) {
        this.date = date;
    }


    public String getamount() {
        return amount;
    }
    public void setamount(String amount) {
        this.amount = amount;
    }

    public String getnumber(){
        return  number;
    }
    public  void setnumber(String number){
        this.number = number;
    }


}
