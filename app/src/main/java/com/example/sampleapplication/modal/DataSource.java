package com.example.sampleapplication.modal;

import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.NetworkStatus;

/**
 * Created by ramkrishna 09/01/2020
 */
public interface DataSource {
    /**
     * Gets data from repository
     *
     * @param networkStatus    network validator
     * @param callbackListener listener
     */
    void getData(NetworkStatus networkStatus, DataCallbackListener callbackListener);

    /**
     * Reset repository
     */
    void reset();
}
