package com.dani.cloovy;

import java.io.Serializable;

public class Product implements Serializable {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    private String productname;
    private String desc;
    private Integer qty;



    public Product(){

    }
        public Product(String key, String productname , String desc , Integer qty){
        this.key=key;
        this.productname=productname;
        this.desc=desc;
        this.qty=qty;
    }

}
