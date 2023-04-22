package com.fakturapp.model;

import com.x5.util.AccessAsBean;

@AccessAsBean
public class TestowyUser extends BaseEntity{

    private String name;
    private Address address;

    public TestowyUser(){

    }

    public TestowyUser(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public TestowyUser(int id, String name, Address address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
