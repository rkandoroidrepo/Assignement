package com.example.sampleapplication;

import com.example.sampleapplication.data.Repository;
import com.example.sampleapplication.data.modal.RowData;
import com.example.sampleapplication.presenter.ListPresenter;
import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.ErrorCode;
import com.example.sampleapplication.utils.NetworkStatus;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyBoolean;

public class ListPresenterTest {

    @Mock
    private NetworkStatus networkStatus;
    private FeedsContract.Presenter presenter;
    @Mock
    private Repository repository;
    @Mock
    private FeedsContract.View view;
    @Captor
    private ArgumentCaptor<NetworkStatus> networkStatusArgumentCaptor;
    @Captor
    private ArgumentCaptor<DataCallbackListener> listenerArgumentCaptor;

    @Before
    public void presenterSetup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ListPresenter(view, repository);
        view.setPresenter(presenter);
    }

    @Test
    public void testStart() {
        presenter.start();
        Mockito.verify(view, Mockito.times(1)).initUI();
    }

    @Test
    public void testGetDataDisplayFeeds() {
        Mockito.when(networkStatus.isOnline()).thenReturn(true);
        presenter.getFeeds(networkStatus, true);
        Mockito.verify(repository).getFeeds(anyBoolean(), networkStatusArgumentCaptor.capture(),
                listenerArgumentCaptor.capture());
        RowData rowData = Utils.loadFeeds();
        listenerArgumentCaptor.getValue().onSuccess(rowData);
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showIndicator(true);
        inOrder.verify(view).showErrorView(false);
        inOrder.verify(view).showIndicator(false);
        inOrder.verify(view).showContentView(true);
        inOrder.verify(view).updateActionBarTitle(rowData.getTitle());
        inOrder.verify(view).showFeeds(rowData.getRows());
    }

    @Test
    public void testGetDataNetworkError() {
        Mockito.when(networkStatus.isOnline()).thenReturn(true);
        presenter.getFeeds(networkStatus, true);
        Mockito.verify(repository).getFeeds(anyBoolean(), networkStatusArgumentCaptor.capture(),
                listenerArgumentCaptor.capture());
        listenerArgumentCaptor.getValue().onError(ErrorCode.NETWORK_ERROR);
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showIndicator(true);
        inOrder.verify(view).showErrorView(false);
        inOrder.verify(view).showIndicator(false);
        inOrder.verify(view).showContentView(false);
        inOrder.verify(view).showError(ErrorCode.NETWORK_ERROR);
    }

}
