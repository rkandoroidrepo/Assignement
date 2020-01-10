package com.example.sampleapplication.presenter;

import com.example.sampleapplication.FeedsContract;
import com.example.sampleapplication.data.Repository;
import com.example.sampleapplication.data.modal.RowData;
import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.NetworkStatus;

/**
 * Created by ramkrishna 20/01/2020
 */
public class FeedsPresenter implements FeedsContract.Presenter {
    private FeedsContract.View view;
    private Repository repository;

    public FeedsPresenter(FeedsContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;
        this.view.setPresenter(this);
    }

    @Override
    public void getFeeds(NetworkStatus networkStatus, boolean fromCache) {
        //Show loading indicator
        view.showIndicator(true);
        //Hide error layout
        view.showErrorView(false);
        repository.getFeeds(fromCache, networkStatus, new DataCallbackListener() {
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
