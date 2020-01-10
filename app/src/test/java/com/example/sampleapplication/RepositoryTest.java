package com.example.sampleapplication;

import com.example.sampleapplication.data.RemoteDataSource;
import com.example.sampleapplication.data.Repository;
import com.example.sampleapplication.data.RepositoryIml;
import com.example.sampleapplication.data.modal.RowData;
import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.ErrorCode;
import com.example.sampleapplication.utils.NetworkStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryTest {
    private Repository repository;
    @Mock
    private RemoteDataSource dataSource;

    @Mock
    private NetworkStatus networkStatus;
    @Captor
    private ArgumentCaptor<NetworkStatus> networkStatusArgumentCaptor;
    @Mock
    private DataCallbackListener callbackListener;
    @Captor
    private ArgumentCaptor<DataCallbackListener> callbackListenerArgumentCaptor;

    @Before
    public void repositorySetup() {
        MockitoAnnotations.initMocks(this);
        repository = RepositoryIml.getInstance(dataSource);
    }

    @Test
    public void testGetDataWithSuccess() {
        when(networkStatus.isOnline()).thenReturn(true);
        repository.getFeeds(false, networkStatus, callbackListener);
        verify(dataSource).getFeeds(networkStatusArgumentCaptor.capture(),
                callbackListenerArgumentCaptor.capture());
        RowData rowData = Utils.loadFeeds();
        // data stubbed
        callbackListenerArgumentCaptor.getValue().onSuccess(rowData);
        // verify onSuccess invoked with RowData object
        verify(callbackListener, times(1)).onSuccess(any(RowData.class));

    }

    @Test
    public void testGetDataWithNetworkError() {
        when(networkStatus.isOnline()).thenReturn(false);
        repository.getFeeds(false, networkStatus, callbackListener);
        verify(dataSource, times(1)).getFeeds(networkStatusArgumentCaptor.capture(),
                callbackListenerArgumentCaptor.capture());
        callbackListenerArgumentCaptor.getValue().onError(ErrorCode.NETWORK_ERROR);
        verify(callbackListener, times(1)).onError(ErrorCode.NETWORK_ERROR);
    }

    @Test
    public void testGetDataWithServerError() {
        when(networkStatus.isOnline()).thenReturn(true);
        repository.getFeeds(false, networkStatus, callbackListener);
        verify(dataSource, times(1)).getFeeds(networkStatusArgumentCaptor.capture(),
                callbackListenerArgumentCaptor.capture());
        callbackListenerArgumentCaptor.getValue().onError(ErrorCode.SERVER_ERROR);
        verify(callbackListener, times(1)).onError(ErrorCode.SERVER_ERROR);
    }

    @After
    public void reset() {
        repository.reset();
    }
}
