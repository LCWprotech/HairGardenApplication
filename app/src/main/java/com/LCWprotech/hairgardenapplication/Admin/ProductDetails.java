package com.LCWprotech.hairgardenapplication.Admin;

public class ProductDetails {

    public String Name,Quantity,Price,Description,ImageURL,RandomUID,AdminId;

    public ProductDetails(String name, String quantity, String price, String description, String imageURL, String randomUID, String adminid) {
        Name = name;
        Quantity = quantity;
        Price = price;
        Description = description;
        ImageURL = imageURL;
        RandomUID = randomUID;
        AdminId = adminid;
    }
}