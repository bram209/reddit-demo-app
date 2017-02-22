package com.example.bram.reddit.lib;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.CallSuper;

/**
 * Created by bram on 2/21/17.
 */

public abstract class ViewModel<V extends MyView> extends BaseObservable {
    private V view;

    @CallSuper
    public void attachView(V view, Bundle savedInstanceState) {
        this.view = view;

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    @CallSuper
    public void detachView() {
        view = null;
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {}

    protected void onSaveInstanceState(Bundle outState) {}
}
