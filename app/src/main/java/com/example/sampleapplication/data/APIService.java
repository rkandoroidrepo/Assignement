package com.example.sampleapplication.data;

import com.example.sampleapplication.data.modal.RowData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ramkrishna 09/01/2020
 */
public interface APIService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<RowData> getRows();
}
