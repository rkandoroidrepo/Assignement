package com.example.sampleapplication;

import com.example.sampleapplication.baseclasses.BasePresenter;
import com.example.sampleapplication.baseclasses.BaseView;
import com.example.sampleapplication.utils.ErrorCode;

/**
 * Created by ramkrishna 09/01/2020
 * This specifies the contract between the view and the presenter.
 */
public interface ListContract {

    interface View extends BaseView<Presenter>{
        /**
         * Initialise ui component
         */
        void initUI();

        /**
         * Show error message based on error code
         * @param errorCode error code defined in ErrorCode class
         */
        void showError(ErrorCode errorCode);
    }

    interface Presenter extends BasePresenter{

    }
}
