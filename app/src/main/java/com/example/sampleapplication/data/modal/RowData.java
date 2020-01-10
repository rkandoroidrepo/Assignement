package com.example.sampleapplication.data.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramkrishna 09/01/2020
 */
public class RowData {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<Row> rows;

    //Copy constructor
    public RowData(RowData other) {
        this.title = other.title;
        List<Row> deepCopy = new ArrayList<>();
        for (Row row : other.rows) {
            deepCopy.add(new Row(row));
        }
        this.rows = deepCopy;
    }

    public String getTitle() {
        return title;
    }

    public List<Row> getRows() {
        return rows;
    }
}
