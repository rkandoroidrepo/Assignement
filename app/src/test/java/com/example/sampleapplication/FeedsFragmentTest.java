package com.example.sampleapplication;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sampleapplication.data.modal.Row;
import com.example.sampleapplication.presenter.FeedsPresenter;
import com.example.sampleapplication.utils.NetworkStatus;
import com.example.sampleapplication.view.FeedsAdapter;
import com.example.sampleapplication.view.FeedsFragment;
import com.example.sampleapplication.view.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyBoolean;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.O_MR1)
public class FeedsFragmentTest {
    private MainActivity activity = Robolectric.setupActivity(MainActivity.class);
    private FeedsFragment feedsFragmentUnderTest;
    private View view;
    @Mock
    private FeedsPresenter presenter;
    @Captor
    private ArgumentCaptor<NetworkStatus> networkStatusCaptor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.feedsFragmentUnderTest = (FeedsFragment) this.activity.getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        this.view = feedsFragmentUnderTest.getView();
        this.feedsFragmentUnderTest.setPresenter(presenter);
    }

    @Test
    public void fragmentIsNotNull() {
        assertNotNull("Fragment should not be null", feedsFragmentUnderTest);
    }

    @Test
    public void testShowIndicator() {
        // Setup
        final boolean show = true;

        // Run the test
        feedsFragmentUnderTest.showIndicator(show);

        // Verify the results
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        assertEquals("Swipe to refresh should be visible", true,
                swipeRefreshLayout.isRefreshing());
    }

    @Test
    public void testUpdateActionBarTitle() {
        // Setup
        final String text = "text";

        // Run the test
        feedsFragmentUnderTest.updateActionBarTitle(text);

        // Verify the results
        String titleTextView = activity.getSupportActionBar().getTitle().toString();
        assertEquals("ActionBar title should be text", text, titleTextView);
    }

    @Test
    public void testShowFeeds() {
        // Setup
        final List<Row> rows = Utils.loadFeeds().getRows();
        ListView listView = view.findViewById(R.id.feeds_list_view);
        View errorView = view.findViewById(R.id.error_layout);
        FeedsAdapter adapter = (FeedsAdapter) listView.getAdapter();

        // Run the test
        feedsFragmentUnderTest.showFeeds(rows);

        // Verify the results
        assertEquals("Feeds should be visible", View.VISIBLE, listView.getVisibility());
        assertNotNull("FeedsAdapter should not be null", adapter);
        assertEquals("Row count should be equal", rows.size(), adapter.getCount());
        assertEquals("Error should not be visible", View.GONE, errorView.getVisibility());
    }

    @Test
    public void testShowErrorView() {
        // Setup
        final boolean show = true;

        // Run the test
        feedsFragmentUnderTest.showErrorView(show);

        // Verify the results
        View errorView = view.findViewById(R.id.error_layout);
        assertEquals("Error should not be visible", View.VISIBLE, errorView.getVisibility());
    }

    @Test
    public void testShowContentView() {
        // Setup
        final boolean show = false;

        // Run the test
        feedsFragmentUnderTest.showContentView(show);

        // Verify the results
        ListView listView = view.findViewById(R.id.feeds_list_view);
        assertEquals("Feeds should be hidden", View.GONE, listView.getVisibility());

    }

    @Test
    public void testOnRefresh() {
        // Setup
        feedsFragmentUnderTest.initUI();
        // Run the test
        feedsFragmentUnderTest.onRefresh();

        // Verify the results
        Mockito.verify(presenter).getFeeds(networkStatusCaptor.capture(), anyBoolean());
    }

    @Test
    public void testOnRetryClick() {
        // Setup
        feedsFragmentUnderTest.initUI();
        Button retryButton = view.findViewById(R.id.button_retry);
        // Run the test
        feedsFragmentUnderTest.onClick(retryButton);

        // Verify the results
        Mockito.verify(presenter).getFeeds(networkStatusCaptor.capture(), anyBoolean());
    }


    @Test
    public void testNewInstance() {
        // Run the test
        final FeedsFragment result = FeedsFragment.newInstance();

        // Verify the results
        assertNotNull(result);
    }
}
