package com.dani.cloovy;

import java.io.Serializable;

public class Store implements Serializable {
    private String key;
    private String namestore;
    private String address;
    private Integer telp;

    public Store(){

    }
    public Store(String key, String namestore , String address , Integer telp){
            this.key=key;
            this.namestore=namestore;
            this.address=address;
            this.telp=telp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamestore() {
        return namestore;
    }

    public void setNamestore(String namestore) {
        this.namestore = namestore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelp() {
        return telp;
    }

    public void setTelp(Integer telp) {
        this.telp = telp;
    }
}
