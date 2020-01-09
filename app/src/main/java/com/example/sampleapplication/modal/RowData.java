package com.example.sampleapplication.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RowData {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<Row> rows = null;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
