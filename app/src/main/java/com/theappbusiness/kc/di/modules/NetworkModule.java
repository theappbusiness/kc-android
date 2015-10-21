package com.theappbusiness.kc.di.modules;

import com.theappbusiness.kc.di.scopes.PerApplication;
import com.theappbusiness.kc.network.NetworkHelper;
import com.theappbusiness.kc.network.RetrofitNetworkHelper;

import dagger.Module;
import dagger.Provides;

/**
 * TODO Add a class header comment
 */
@Module
public class NetworkModule {

    @Provides
    @PerApplication
    NetworkHelper provideNetworkHelper(RetrofitNetworkHelper networkHelper) {
        return networkHelper;
    }
    
}
