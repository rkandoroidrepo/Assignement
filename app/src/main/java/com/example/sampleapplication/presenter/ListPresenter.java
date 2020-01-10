package com.example.sampleapplication.presenter;

import com.example.sampleapplication.ListContract;
import com.example.sampleapplication.modal.Repository;
import com.example.sampleapplication.modal.RowData;
import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.NetworkStatus;

public class ListPresenter implements ListContract.Presenter {
    private ListContract.View view;
    private Repository repository;

    public ListPresenter(ListContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getData(NetworkStatus networkStatus, boolean fromCache){
        view.showIndicator(true);
        repository.getData(fromCache, networkStatus, new DataCallbackListener() {
            @Override
            public void onSuccess(RowData rowData) {
                view.showIndicator(false);
                if(rowData.getTitle()!=null) {
                    view.updateActionBarTitle(rowData.getTitle());
                }
                if(!rowData.getRows().isEmpty()) {
                    view.showFeeds(rowData.getRows());
                }
            }

            @Override
            public void onError(int errorCode) {
                view.showIndicator(false);
            }
        });
    }

    @Override
    public void start() {
        view.initUI();
    }
}
