package com.example.sampleapplication.modal;

import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.ErrorCode;
import com.example.sampleapplication.utils.NetworkStatus;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private static int getErrorCode(Throwable t) {
        if (t instanceof IOException) {
            return ErrorCode.NETWORK_ERROR;
        } else {
            return ErrorCode.SERVER_ERROR;
        }
    }

    @Override
    public void getData(NetworkStatus networkStatus, final DataCallbackListener callbackListener) {
        if (networkStatus.isOnline()) {
            apiService.getRows().enqueue(new Callback<RowData>() {
                @Override
                public void onResponse(Call<RowData> call, Response<RowData> response) {
                    if (response.isSuccessful()) {
                        callbackListener.onSuccess(response.body());
                    } else {
                        callbackListener.onError(ErrorCode.SERVER_ERROR);
                    }
                }

                @Override
                public void onFailure(Call<RowData> call, Throwable t) {
                    callbackListener.onError(getErrorCode(t));
                }
            });
        } else {
            callbackListener.onError(ErrorCode.NETWORK_ERROR);
        }
    }

    @Override
    public void reset() {
        apiService = null;
        remoteDataSource.reset();
    }
}
