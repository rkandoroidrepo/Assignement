package com.example.sampleapplication;

import com.example.sampleapplication.baseclasses.BasePresenter;
import com.example.sampleapplication.baseclasses.BaseView;
import com.example.sampleapplication.modal.Row;
import com.example.sampleapplication.utils.ErrorCode;
import com.example.sampleapplication.utils.NetworkStatus;

import java.util.List;

/**
 * Created by ramkrishna 09/01/2020
 * This specifies the contract between the view and the presenter.
 */
public interface ListContract {

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
        void showError(ErrorCode errorCode);

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
    }

    interface Presenter extends BasePresenter {
        void getData(NetworkStatus networkStatus, boolean fromCache);
    }
}
