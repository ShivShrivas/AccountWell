package com.bsninfotech.accountwell.Helper;

public class User_Helper {
    String Name,Designation,Department,Address,ContactNo,Email,UserName,PhotoPath,Code,DateofJoining,CompanyName,CompanyBranchName;
    User_Helper(){}
    public User_Helper(String name, String designation, String department, String address, String contactNo, String email, String userName, String photoPath, String code, String dateofJoining, String companyName, String companyBranchName) {
        Name = name;
        Designation = designation;
        Department = department;
        Address = address;
        ContactNo = contactNo;
        Email = email;
        UserName = userName;
        PhotoPath = photoPath;
        Code = code;
        DateofJoining = dateofJoining;
        CompanyName = companyName;
        CompanyBranchName = companyBranchName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        PhotoPath = photoPath;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDateofJoining() {
        return DateofJoining;
    }

    public void setDateofJoining(String dateofJoining) {
        DateofJoining = dateofJoining;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyBranchName() {
        return CompanyBranchName;
    }

    public void setCompanyBranchName(String companyBranchName) {
        CompanyBranchName = companyBranchName;
    }
}
