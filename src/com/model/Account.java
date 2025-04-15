
package com.model;

public class Account {
    private int userId;
    private String userName;
    private String userPass;
    private String userFirstname;
    private String userLastname;
    private String userAddress;
    private String userContactNo;
    private boolean userIsAdmin;

    // Default constructor
    public Account() {}

    // Parameterized constructor
    public Account(int userId, String userName, String userPass, String userFirstname,
                   String userLastname, String userAddress, String userContactNo, boolean userIsAdmin) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.userAddress = userAddress;
        this.userContactNo = userContactNo;
        this.userIsAdmin = userIsAdmin;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserContactNo() {
        return userContactNo;
    }

    public void setUserContactNo(String userContactNo) {
        this.userContactNo = userContactNo;
    }

    public boolean isUserIsAdmin() {
        return userIsAdmin;
    }

    public void setUserIsAdmin(boolean userIsAdmin) {
        this.userIsAdmin = userIsAdmin;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userFirstname='" + userFirstname + '\'' +
                ", userLastname='" + userLastname + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userContactNo='" + userContactNo + '\'' +
                ", userIsAdmin=" + userIsAdmin +
                '}';
    }
}
