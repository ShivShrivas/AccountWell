package com.bsninfotech.accountwell.Helper;

public class BankSummary_Helper {
    String Comp_name;
    String CompAddress;
    String DoctorMobile;
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



    public BankSummary_Helper(String Comp_name, String CompAddress,String Purpose){

      this.Comp_name=Comp_name;
      this.CompAddress=CompAddress;
      this.DoctorMobile=DoctorMobile;
      this.Qualification=Qualification;
      this.DoctorProfilePic=DoctorProfilePic;
      this.AppoinedTime=AppoinedTime;
      this.AppointmentDate=AppointmentDate;
      this.Purpose=Purpose;
      this.MRAppointmentId=MRAppointmentId;
      this.MapLocation=MapLocation;
      this.ProductName=ProductName;
      this.Rating=Rating;
      this.StartOn=StartOn;
      this.DStatus=DStatus;

    }



    public String getCompname() {
        return Comp_name;
    }
    public void setCompname(String Comp_name) {
        this.Comp_name = Comp_name;
    }


    public String getCompAddress() {
        return CompAddress;
    }
    public void setCompAddress(String CompAddress) {
        this.CompAddress = CompAddress;
    }

    public String getDoctorMobile(){
        return  DoctorMobile;
    }
    public  void setDoctorMobile(String DoctorMobile){
        this.DoctorMobile = DoctorMobile;
    }


    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String Qualification) {
        this.Qualification = Qualification;
    }

    public String getDoctorProfilePic() {
        return DoctorProfilePic;
    }

    public void setDoctorProfilePic(String DoctorProfilePic) {
        this.DoctorProfilePic = DoctorProfilePic;
    }
    public String getAppoinedTime() {
        return AppoinedTime;
    }

    public void setAppoinedTime(String AppoinedTime) {
        this.AppoinedTime = AppoinedTime;
    }


    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String AppointmentDate) {
        this.AppointmentDate = AppointmentDate;
    }


       public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String Purpose) {
        this.Purpose = Purpose;
    }

    public String getMRAppointmentId() {
        return MRAppointmentId;
    }

    public void setMRAppointmentId(String MRAppointmentId) {
        this.MRAppointmentId = MRAppointmentId;
    }

    public String getMapLocation() {
        return MapLocation;
    }

    public void setMapLocation(String MapLocation) {
        this.MapLocation = MapLocation;
    }


    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }


    public String getRating() {
        return Rating;
    }

    public void setRating(String Rating) {
        this.Rating = Rating;
    }
    public String getStartOn() {
        return StartOn;
    }

    public void setStartOn(String StartOn) {
        this.StartOn = StartOn;
    }

    public String getDStatus() {
        return DStatus;
    }

    public void setDStatus(String DStatus) {
        this.DStatus = DStatus;
    }


}
