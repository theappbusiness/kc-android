package com.theappbusiness.kc.network.categories;

import android.os.Looper;

import com.theappbusiness.kc.controllers.categories.CategoriesController;
import com.theappbusiness.kc.model.CategoriesResponse;
import com.theappbusiness.kc.model.Category;
import com.theappbusiness.kc.network.NetworkHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * TODO Add a class header comment
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Schedulers.class, AndroidSchedulers.class, Looper.class})
public class CategoriesNetworkManagerImplTest {
    @Mock
    private NetworkHelper mNetworkHelper;
    @Mock
    private CategoriesController mController;

    private CategoriesNetworkManager mNetworkManager;

    @Before
    public void setUp() throws Exception {
        Scheduler immediate = Schedulers.immediate();

        mockStatic(Schedulers.class, Looper.class, AndroidSchedulers.class);
        PowerMockito.when(Schedulers.io()).thenReturn(immediate);
        PowerMockito.when(Looper.getMainLooper()).thenReturn(mock(Looper.class));
        PowerMockito.when(AndroidSchedulers.mainThread()).thenReturn(immediate);

        mNetworkManager = new CategoriesNetworkManagerImpl(mNetworkHelper, mController);
    }

    @Test
    public void testRequestCategories() throws Exception {
        CategoriesResponse response = mock(CategoriesResponse.class);
        List<Category> categories = new ArrayList<>();
        PowerMockito.when(response.getCategories()).thenReturn(categories);
        when(mNetworkHelper.requestCategories()).thenReturn(response);

        mNetworkManager.requestCategories();
        verify(mNetworkHelper).requestCategories();
        verify(mController).onCategoriesLoaded(categories);
    }

    @Test
    public void testRequestCategoriesError() throws Exception {
        doThrow(Exception.class).when(mNetworkHelper).requestCategories();

        mNetworkManager.requestCategories();
        verify(mNetworkHelper).requestCategories();
        verify(mController, never()).onCategoriesLoaded(anyObject());
    }

    @Test
    public void testCancelRequests() throws Exception {
        Subscription subscription = mock(Subscription.class);

        Whitebox.setInternalState(mNetworkManager, "mSubscription", subscription);
        mNetworkManager.cancelRequests();
        verify(subscription).unsubscribe();
        assertThat(Whitebox.getInternalState(mNetworkManager, "mSubscription")).isNull();
    }

    @Test
    public void testCancelRequestsNullSubscription() throws Exception {
        mNetworkManager.cancelRequests();
        assertThat(Whitebox.getInternalState(mNetworkManager, "mSubscription")).isNull();
    }
}
