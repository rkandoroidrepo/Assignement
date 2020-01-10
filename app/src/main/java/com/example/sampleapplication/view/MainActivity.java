package com.example.sampleapplication.view;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapplication.R;
import com.example.sampleapplication.modal.RemoteDataSource;
import com.example.sampleapplication.modal.RepositoryIml;
import com.example.sampleapplication.presenter.ListPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ListFragment listFragment = ListFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, listFragment)
                    .commit();
            new ListPresenter(listFragment, RepositoryIml.getInstance(RemoteDataSource.getInstance()));

        }

        ActionBar actionBar = getSupportActionBar();
    }
}
