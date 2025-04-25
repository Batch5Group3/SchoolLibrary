
package com.model;


public class AccountModel {
    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String contactNo;
    private boolean isAdmin;

    public AccountModel() {
    }
    
    public AccountModel(String userName, String password, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

     public AccountModel(int usrId, String userName, String password, String firstName, String lastName, String address, String contactNo, boolean isAdmin) {
        this.id = usrId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNo = contactNo;
        this.isAdmin = isAdmin;
    }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String pass) {
        this.password = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "AccountModel{" + "id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", contactNo=" + contactNo + ", isAdmin=" + isAdmin + '}';
    }
}
