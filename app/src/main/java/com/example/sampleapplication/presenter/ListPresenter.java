package com.example.sampleapplication.presenter;

import com.example.sampleapplication.ListContract;

public class ListPresenter implements ListContract.Presenter {
    private ListContract.View view;
    public ListPresenter(ListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.initUI();
    }
}
