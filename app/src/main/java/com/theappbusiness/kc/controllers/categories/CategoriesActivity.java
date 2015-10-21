package com.theappbusiness.kc.controllers.categories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theappbusiness.kc.KcApp;
import com.theappbusiness.kc.R;
import com.theappbusiness.kc.di.components.CategoriesComponent;
import com.theappbusiness.kc.di.modules.CategoriesModule;
import com.theappbusiness.kc.model.Category;
import com.theappbusiness.kc.network.categories.CategoriesNetworkManager;
import com.theappbusiness.kc.persistence.Database;
import com.theappbusiness.kc.view.categories.CategoriesPresentation;

import java.util.List;

import javax.inject.Inject;

/**
 * Acts as {@link CategoriesController}. Delegates presentations tasks to {@link
 * CategoriesPresentation} and com.theappbusiness.kc.network tasks to {@link CategoriesManagerImpl}
 */
public class CategoriesActivity extends AppCompatActivity implements CategoriesController {

    private CategoriesComponent mComponent;

    @Inject
    CategoriesPresentation mPresentation;

    @Inject
    CategoriesNetworkManager mNetworkManager;

    @Inject
    Database mDatabase;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        mComponent = KcApp.get().getComponent().plus(new CategoriesModule(this));
        mComponent.inject(this);
        mPresentation.init(findViewById(android.R.id.content));
    }

    @Override
    public void onStart() {
        super.onStart();
        mNetworkManager.requestCategories();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNetworkManager.cancelRequests();
    }

    @Override
    public void onCategoriesLoaded(List<Category> categories) {
        mPresentation.showCategories(categories);
    }

    /**
     * Used by fragment to extend component
     *
     * @return instance of component
     */
    public CategoriesComponent getComponent() {
        return mComponent;
    }
}

