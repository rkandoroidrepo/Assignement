package com.example.sampleapplication.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sampleapplication.ListContract;
import com.example.sampleapplication.R;
import com.example.sampleapplication.presenter.ListPresenter;
import com.example.sampleapplication.utils.ErrorCode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements ListContract.View {

    public ListFragment() {
        // Required empty public constructor
    }
    private ListContract.Presenter presenter;
    private View rootView;

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        rootView =  inflater.inflate(R.layout.fragment_list, container, false);
        presenter = new ListPresenter(this);
        presenter.start();
        return rootView;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void showError(ErrorCode errorCode) {

    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {

    }
}
