package com.theappbusiness.kc.io;

import com.theappbusiness.kc.model.Category;

import java.util.List;

import rx.Observable;

/**
 * Created by swav on 09/10/15.
 */
public interface CategoriesController extends GenericController<List<Category>> {
    void showError();

    Observable<List<Category>> queryBackend();

    void showContent(List<Category> categories);
}
