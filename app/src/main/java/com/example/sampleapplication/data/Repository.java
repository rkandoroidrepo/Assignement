package com.example.sampleapplication.data;

import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.NetworkStatus;

/**
 * Created by ramkrishna 09/01/2020
 * Main entry point for accessing feeds
 * For simplicity, only getFeeds() have callbacks. Consider adding callbacks to other
 * methods to inform the user of network errors or successful operations.
 */
public interface Repository {

    /**
     * Gets data from repository
     *
     * @param fromCache        if true pass cached data in callback(if available)
     *                         else network call
     * @param networkStatus    network validator
     * @param callbackListener listener
     */
    void getFeeds(boolean fromCache, NetworkStatus networkStatus,
                  DataCallbackListener callbackListener);

    /**
     * Reset repository
     */
    void reset();
}
