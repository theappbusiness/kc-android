package com.theappbusiness.kc.io;

import rx.Observable;

/**
 * Generic controller for all our needs
 */
public interface GenericController<T> {

    void showError();

    Observable<T> queryBackend();

    void showContent(T categories);
}
