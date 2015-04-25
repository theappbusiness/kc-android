package com.nabilhachicha.kc.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nabil on 25/04/15.
 *"id": "1",
 "category_id": "BUILDING",
 "name": "GNH Bar",
 "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
 "commentary": "Tucked away in a courtyard accessible via an alley at the foot of Pentonville Road, this rustic, Andaluc?an-themed bar is dedicated to sherry. With room for only four or five tables, all shaped from sherry casks, it?s a tiny place.\n\nThe 15 sherries span all styles and you can sample the breadth",
 "address": "20-24 broadwick street W1H F1H",
 "opening_times": "9-5 Mon-Friday",
 "phone_number": "+44123123123",
 "website": "http://example.com/",
 "email": "info@example.com",
 "lattitude": 51.532327,
 "longitude": -0.1167457,
 "image_url": "http://s17.postimg.org/d1uxyzyzz/IMG_0007_2.jpg"

 */


public class Venue implements Serializable { //TODO use Parcelable
    private String id;

    @SerializedName("category_id")
    private String categoryId;

    private String name;

    private String description;

    private String commentary;

    private String address;

    @SerializedName("opening_times")
    private String openingTimes;

    @SerializedName("phone_number")
    private String phoneNumber;

    private String website;

    private String email;

    private double lattitude;

    private double longitude;

    @SerializedName("image_url")
    private String imageUrl;


    //Getter/Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningTimes() {
        return openingTimes;
    }

    public void setOpeningTimes(String openingTimes) {
        this.openingTimes = openingTimes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
