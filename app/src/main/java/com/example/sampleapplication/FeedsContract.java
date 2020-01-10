package com.example.sampleapplication;

import com.example.sampleapplication.baseclasses.BasePresenter;
import com.example.sampleapplication.baseclasses.BaseView;
import com.example.sampleapplication.data.modal.Row;
import com.example.sampleapplication.utils.NetworkStatus;

import java.util.List;

/**
 * Created by ramkrishna 09/01/2020
 * This specifies the contract between the view and the presenter.
 */
public interface FeedsContract {

    interface View extends BaseView<Presenter> {
        /**
         * Initialise ui component
         */
        void initUI();

        /**
         * Show error message based on error code
         *
         * @param errorCode error code defined in ErrorCode class
         */
        void showError(int errorCode);

        /**
         * Show/hide progress bar
         *
         * @param show flag
         */
        void showIndicator(boolean show);

        /**
         * Update action bar title
         *
         * @param text title from json feed
         */
        void updateActionBarTitle(String text);

        /**
         * Show json feeds in listView
         *
         * @param rows data
         */
        void showFeeds(List<Row> rows);

        /**
         * Show/hide error layout
         *
         * @param show
         */
        void showErrorView(boolean show);

        /**
         * Show/hide content list
         *
         * @param show
         */
        void showContentView(boolean show);
    }

    interface Presenter extends BasePresenter {
        /**
         * Gets data from depository
         *
         * @param networkStatus network validator
         * @param fromCache     if true and data available in repository
         *                      then return cached data else request for server
         */
        void getFeeds(NetworkStatus networkStatus, boolean fromCache);
    }
}
