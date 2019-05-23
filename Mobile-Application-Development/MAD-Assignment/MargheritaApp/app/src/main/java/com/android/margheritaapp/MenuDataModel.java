package com.android.margheritaapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MenuDataModel {

    @SerializedName("Menu_id")
    @Expose
    private String Menu_id;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("Image")
    @Expose
    private String Image;

    @SerializedName("Price")
    @Expose
    private String Price;

    @SerializedName("Description")
    @Expose
    private String Description;

    public String getMenu_id() {
        return Menu_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

}