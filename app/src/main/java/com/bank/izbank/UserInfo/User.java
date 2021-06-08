package com.bank.izbank.UserInfo;

import com.bank.izbank.Bill.Bill;

import java.util.ArrayList;

public class User {
    private String name;
    private String id;
    private String pass;
  //  private String surname;
    private String phoneNumber;
    private Address address;
    private String profession;
    private String addressSum;
    private ArrayList<CreditCard> creditcards;
    private ArrayList<BankAccount> bankAccounts;
    private ArrayList<Bill> userbills;

    public User(String name, String id,String pass,String phoneNumber, Address address, String profession) {
        this.name = name;
        this.id=id;
        this.pass=pass;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profession = profession;
        this.creditcards = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
    }

    public User(String name, String id,String phoneNumber, Address address, String profession) {
        this.name = name;
        this.id=id;
        this.pass=null;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profession = profession;
        this.creditcards = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void setCreditcards(ArrayList<CreditCard> creditcards) {
        this.creditcards = creditcards;
    }

    public ArrayList<CreditCard> getCreditcards() {
        return creditcards;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String addressWrite(){
     addressSum= address.getStreet()+" "+address.getNeighborhood()+" "+address.getApartmentNumber()+" "+address.getFloor()+" "+address.getHomeNumber()+" "+address.getCity()+" "+address.getProvince()+" "+address.getCountry();
     return addressSum;
    }

    public ArrayList<Bill> getUserbills() {
        return userbills;
    }

    public void setUserbills(ArrayList<Bill> userbills) {
        this.userbills = userbills;
    }
}
