package com.nabilhachicha.kc.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nabil on 25/04/15.
 *"id": "CHEAP",
 "name": "Welcome to the Spitfire Building",
 "description": "We built this handy little app as a useful tool to help us get to know a new neighbourhood",
 "position": 0,
 "logo_url": "http://www.highgatestudios.com/wp-content/uploads/2012/06/Spitfire.jpeg",
 "items_feed_url": "http://www.highgatestudios.com/wp-content/feed_0"

 */
public class Category implements Serializable {
    private String id;
    private String name;
    private String description;
    private int position;

    @SerializedName("items_feed_url")
    private String itemFeedUrl;

    @SerializedName("logo_url")
    private String logoUrl;

    //Getter/Setter

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getItemFeedUrl() {
        return itemFeedUrl;
    }

    public void setItemFeedUrl(String itemFeedUrl) {
        this.itemFeedUrl = itemFeedUrl;
    }
}
