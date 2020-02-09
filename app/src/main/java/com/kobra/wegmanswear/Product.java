package com.kobra.wegmanswear;

public class Product {
    private String product_name = "";

    public Product(String name) {
        this.product_name = name;
    }

    public String getProductName() {return product_name;}

    public void setProductName(String name) {this.product_name = name;}
}
