package com.example.sampleapplication.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapplication.R;
import com.example.sampleapplication.data.RemoteDataSource;
import com.example.sampleapplication.data.RepositoryIml;
import com.example.sampleapplication.presenter.FeedsPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadListFragment();
        }
    }

    private void loadListFragment() {
        FeedsFragment feedsFragment = FeedsFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, feedsFragment)
                .commit();
        new FeedsPresenter(feedsFragment, RepositoryIml.getInstance(RemoteDataSource.getInstance()));

    }
}
