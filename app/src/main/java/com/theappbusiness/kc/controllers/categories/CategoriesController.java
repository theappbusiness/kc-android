package com.theappbusiness.kc.controllers.categories;

import com.theappbusiness.kc.model.Category;

import java.util.List;

/**
 * Concrete interface for {@link Category} controller. We cannot use generics directly in Dagger.
 */
public interface CategoriesController {
    void onCategoriesLoaded(List<Category> categories);
}
