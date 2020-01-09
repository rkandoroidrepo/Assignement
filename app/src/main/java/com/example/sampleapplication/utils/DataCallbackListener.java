package com.example.sampleapplication.utils;

import com.example.sampleapplication.modal.RowData;

/**
 * Created by ramkrishna 09/01/2020
 * Callback interface to interact with repository
 */
public interface DataCallbackListener {
    /**
     * called when the request completed successfully
     * @param rowData
     */
    void onSuccess(RowData rowData);

    /**
     * Called when request failed due to any error
     * @param errorCode
     */
    void onError(int errorCode);
}
