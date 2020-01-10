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
        this.view.setPresenter(this);
    }

    @Override
    public void getData(NetworkStatus networkStatus, boolean fromCache) {
        //Show loading indicator
        view.showIndicator(true);
        //Hide error layout
        view.showErrorView(false);
        repository.getData(fromCache, networkStatus, new DataCallbackListener() {
            @Override
            public void onSuccess(RowData rowData) {
                //Hide loading indicator
                view.showIndicator(false);
                //Show content list
                view.showContentView(true);
                if (rowData.getTitle() != null) {
                    view.updateActionBarTitle(rowData.getTitle());
                }
                if (!rowData.getRows().isEmpty()) {
                    view.showFeeds(rowData.getRows());
                }
            }

            @Override
            public void onError(int errorCode) {
                //Hide loading indicator
                view.showIndicator(false);
                //Hide content list
                view.showContentView(false);
                view.showError(errorCode);
            }
        });
    }

    @Override
    public void start() {
        view.initUI();
    }
}
