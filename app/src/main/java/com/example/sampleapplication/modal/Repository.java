package com.example.sampleapplication.modal;

import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.NetworkStatus;

public interface Repository {

    /**
     * Gets data from repository
     *
     * @param fromCache
     * @param networkStatus
     * @param callbackListener
     */
    void getData(boolean fromCache, NetworkStatus networkStatus,
                 DataCallbackListener callbackListener);

    /**
     * Reset repository
     */
    void reset();
}
