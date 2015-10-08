package com.theappbusiness.kc.di.components;

import com.theappbusiness.kc.KcApp;
import com.theappbusiness.kc.di.modules.KcModule;
import com.theappbusiness.kc.di.scopes.PerApplication;

import dagger.Component;

/**
 * TODO Add a class header comment
 */
@PerApplication
@Component(modules = {KcModule.class})
public interface KcComponent {
    void inject(KcApp application);

}
