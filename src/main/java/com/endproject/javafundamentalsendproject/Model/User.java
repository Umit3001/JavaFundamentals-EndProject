package com.endproject.javafundamentalsendproject.Model;


import java.io.Serializable;

public class User implements Serializable {


    private int employeeId;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private CompanyRole companyRole;

    public User(int employeeId, String firstname,String lastname,String username, String password, CompanyRole companyRole) {
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.companyRole = companyRole;
    }

    public int getEmployeeId() {
        return employeeId;
    }
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public CompanyRole getCompanyRoll() {
        return companyRole;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCompanyRoll(CompanyRole companyRole) {
        this.companyRole = companyRole;
    }
}
