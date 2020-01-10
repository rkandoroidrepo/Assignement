package com.example.sampleapplication.data;

import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.NetworkStatus;

/**
 * Created by ramkrishna 09/01/2020
 */
public interface DataSource {
    /**
     * Gets data from repository
     *
     * @param networkStatus    network validator if is online true
     *                         call service else pass NETWORK_ERROR code in calback
     * @param callbackListener listener
     */
    void getFeeds(NetworkStatus networkStatus, DataCallbackListener callbackListener);

    /**
     * Reset repository
     */
    void reset();
}
