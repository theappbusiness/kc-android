package com.theappbusiness.kc.io;

import rx.Observable;

/**
 * Created by swav on 09/10/15.
 */
public interface GenericController<T> {

    void showError();

    Observable<T> queryBackend();

    void showContent(T categories);
}
