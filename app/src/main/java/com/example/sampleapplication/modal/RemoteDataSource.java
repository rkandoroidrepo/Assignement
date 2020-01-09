package com.example.sampleapplication.modal;

import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.NetworkStatus;

/**
 * Created by ramkrishna 09/01/2020
 */
public class RemoteDataSource implements DataSource {
    private static RemoteDataSource remoteDataSource;
    private APIService apiService;

    private RemoteDataSource() {
        apiService = RetrofitClient.getClient().create(APIService.class);
    }

    public static DataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new RemoteDataSource();
        }
        return remoteDataSource;
    }

    @Override
    public void getData(NetworkStatus networkStatus, DataCallbackListener callbackListener) {

    }

    @Override
    public void reset() {

    }
}
