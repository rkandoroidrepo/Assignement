package com.example.sampleapplication.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

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

    private ListContract.Presenter presenter;
    private View rootView;
    private ListView contentListView;
    public ListFragment() {
        // Required empty public constructor
    }

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
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        presenter = new ListPresenter(this);
        presenter.start();
        return rootView;
    }

    @Override
    public void initUI() {
        contentListView = rootView.findViewById(R.id.content_list_view);
    }

    @Override
    public void showError(ErrorCode errorCode) {

    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {

    }
}
