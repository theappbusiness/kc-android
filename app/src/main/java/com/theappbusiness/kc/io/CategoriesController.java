package com.theappbusiness.kc.io;

import com.theappbusiness.kc.model.Category;

import java.util.List;

import rx.Observable;

/**
 * Concrete interface for {@link Category} controller. We cannot use generics directly in Dagger.
 */
public interface CategoriesController extends GenericController<List<Category>> {
    void showError();

    Observable<List<Category>> queryBackend();

    void showContent(List<Category> categories);
}
