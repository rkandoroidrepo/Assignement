package com.example.sampleapplication.data;

import com.example.sampleapplication.data.modal.RowData;
import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.NetworkStatus;

/**
 * Created by ramkrishna 09/01/2020
 * Implementation of Repository
 */
public class RepositoryIml implements Repository {
    private static Repository repository;
    private RowData rowData;
    private DataSource dataSource;

    private RepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static Repository getInstance(DataSource dataSource) {
        if (repository == null) {
            repository = new RepositoryIml(dataSource);
        }
        return repository;
    }

    @Override
    public void getFeeds(boolean fromCache, NetworkStatus networkStatus,
                         final DataCallbackListener callbackListener) {
        if (fromCache && rowData != null && !rowData.getRows().isEmpty()) {
            //Pass cached data
            //Deep copy passed to avoid any data manipulation
            callbackListener.onSuccess(new RowData(rowData));
        } else {
            //request from remote
            dataSource.getFeeds(networkStatus, new DataCallbackListener() {
                @Override
                public void onSuccess(RowData rows) {
                    //Store data in repo
                    rowData = rows;
                    //Deep copy passed
                    callbackListener.onSuccess(new RowData(rowData));
                }

                @Override
                public void onError(int errorCode) {
                    callbackListener.onError(errorCode);
                }
            });
        }
    }

    @Override
    public void reset() {
        rowData = null;
        dataSource.reset();
        repository = null;
    }

}
