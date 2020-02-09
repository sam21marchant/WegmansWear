package com.kobra.wegmanswear.dataobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WegmansProduct {

    @SerializedName("entry_id")
    @Expose
    private Integer entryId;

    @SerializedName("sku")
    @Expose
    private Integer sku;

    @SerializedName("list_id")
    @Expose
    private Integer listId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("qty")
    @Expose
    private Integer qty;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("checked")
    @Expose
    private Boolean checked;

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}
