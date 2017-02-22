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

    protected abstract void onRestoreInstanceState(Bundle bundle);

    protected abstract void onSaveInstanceState(Bundle outState);
}
