package com.theappbusiness.kc.view.categories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.theappbusiness.kc.KcApp;
import com.theappbusiness.kc.R;
import com.theappbusiness.kc.data.Database;
import com.theappbusiness.kc.di.components.CategoriesComponent;
import com.theappbusiness.kc.di.modules.CategoriesModule;
import com.theappbusiness.kc.di.qualifiers.ForCategories;
import com.theappbusiness.kc.io.CategoriesController;
import com.theappbusiness.kc.io.CategoriesManagerImpl;
import com.theappbusiness.kc.io.KcObservables;
import com.theappbusiness.kc.io.Manager;
import com.theappbusiness.kc.model.Category;
import com.theappbusiness.kc.service.BackendOperations;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Acts as {@link CategoriesController}. Delegates presentations tasks to {@link
 * CategoriesPresentation} and network tasks to {@link CategoriesManagerImpl}
 */
public class CategoriesActivity extends AppCompatActivity implements CategoriesController {

    private CategoriesComponent mComponent;

    @Inject
    CategoriesPresentation categoriesPresentation;

    @Inject
    BackendOperations mBackendOperations;

    @Inject
    Database mDatabase;

    @Inject
    @ForCategories
    Manager mCategoriesHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        mComponent = KcApp.get().getComponent().plus(new CategoriesModule(this));
        mComponent.inject(this);
        categoriesPresentation.init(findViewById(android.R.id.content));

    }

    @Override
    public void onStart() {
        super.onStart();
        mCategoriesHelper.onStart();
    }

    @Override
    public void onStop() {
        mCategoriesHelper.onStop();
        super.onStop();
    }


    @Override
    public void showError() {

    }

    @Override
    public Observable<List<Category>> queryBackend() {
        return KcObservables.getCategories(mBackendOperations, mDatabase);
    }

    @Override
    public void showContent(List<Category> categories) {
        categoriesPresentation.showContent(categories);
    }

    /**
     * Used by fragment to extend component
     * @return instance of component
     */
    public CategoriesComponent getComponent() {
        return mComponent;
    }
}
