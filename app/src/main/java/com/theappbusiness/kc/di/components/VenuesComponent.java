package com.theappbusiness.kc.di.components;

import com.theappbusiness.kc.di.modules.VenuesModule;
import com.theappbusiness.kc.di.scopes.PerActivity;
import com.theappbusiness.kc.view.itemlist.ItemsFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {VenuesModule.class})
@PerActivity
public interface VenuesComponent {
    void inject(ItemsFragment fragment);
}
