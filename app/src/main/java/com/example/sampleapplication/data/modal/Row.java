package com.example.sampleapplication.data.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ramkrishna 09/01/2020
 */
public class Row {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    //Copy constructor
    public Row(Row other) {
        this.title = other.title;
        this.description = other.description;
        this.imageHref = other.imageHref;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }
}
