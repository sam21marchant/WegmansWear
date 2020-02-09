package com.kobra.wegmanswear.dataobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShoppingList {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("data")
    @Expose
    private ArrayList<WegmansProduct> products;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<WegmansProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<WegmansProduct> products) {
        this.products = products;
    }

    public WegmansProduct getProduct(int index){
        return products.get(index);
    }

}
