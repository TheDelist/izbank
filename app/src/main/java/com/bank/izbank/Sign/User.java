package com.bank.izbank.Sign;

public class User {
    private String name;
    private String id;
    private String pass;
  //  private String surname;
    private String phoneNumber;
    private Address address;
    private String profession;
    private String addressSum;

    public User(String name, String id,String pass,String phoneNumber, Address address, String profession) {
        this.name = name;
        this.id=id;
        this.pass=pass;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profession = profession;
    }

    public User(String name, String id,String phoneNumber, Address address, String profession) {
        this.name = name;
        this.id=id;
        this.pass=null;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profession = profession;
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
}
