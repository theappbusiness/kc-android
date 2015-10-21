package com.theappbusiness.kc;

import com.facebook.stetho.Stetho;

/**
 * TODO Add a class header comment
 */
public class KcDebugApplication extends KcApp  {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.Initializer initializer = Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build();
        Stetho.initialize(initializer);
    }
}
