package com.codegym.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String name;
    private int price;
    private int quanlity;

    public Product() {
    }

    public Product(String id, String name, int price,int quanlity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quanlity=quanlity;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID:"+id + ", "+"Tên sản phẩm: " + name + ", Giá: " + price +", Số lượng còn: "+quanlity ;
    }
}
