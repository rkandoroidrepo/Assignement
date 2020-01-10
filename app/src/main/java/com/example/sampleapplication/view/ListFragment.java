package com.example.sampleapplication.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sampleapplication.ListContract;
import com.example.sampleapplication.R;
import com.example.sampleapplication.modal.Row;
import com.example.sampleapplication.utils.AppNetworkStatus;
import com.example.sampleapplication.utils.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements ListContract.View, SwipeRefreshLayout.OnRefreshListener {

    private ListContract.Presenter presenter;
    private View rootView;
    private ListView contentListView;
    private ContentListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

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
        if (presenter != null) {
            presenter.start();
            presenter.getData(new AppNetworkStatus(getContext()), true);
        }
        return rootView;
    }

    @Override
    public void initUI() {
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        contentListView = rootView.findViewById(R.id.content_list_view);
        adapter = new ContentListAdapter(getContext(), new ArrayList<>());
        contentListView.setAdapter(adapter);
    }

    @Override
    public void showError(ErrorCode errorCode) {

    }

    @Override
    public void showIndicator(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void updateActionBarTitle(String text) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    @Override
    public void showFeeds(List<Row> rows) {
        if (adapter != null) {
            adapter.updateListView(rows);
        }
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        presenter.getData(new AppNetworkStatus(getContext()), false);
    }
}
