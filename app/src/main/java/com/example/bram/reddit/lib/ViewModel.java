package com.example.bram.reddit.lib;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.CallSuper;

/**
 * Created by bram on 2/21/17.
 */

public abstract class ViewModel<V extends MyView> extends BaseObservable {
    private V view;

    /**
     * This method gets called when a new view is attached
     * @param view The view that is attached
     */
    @CallSuper
    public void attachView(V view) {
        this.view = view;
    }

    /**
     * This method gets called when the view gets detached.
     * When this view is an activity, it happens on {@link BaseActivity#onDestroy()}
     * and on {@link BaseActivity#onSaveInstanceState(Bundle)}
     */
    @CallSuper
    public void detachView() {
        view = null;
    }

    /**
     * This method gets called when the view restores its instance state.
     * With the MVVM pattern, the ViewModel is responsible for managing state.
     * Therefor the ViewModel is responsible to restore the state appropriately.
     * 
     * This method gets called on {@link BaseActivity#onRestoreInstanceState(Bundle)}
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     */
    protected void onRestoreInstanceState(Bundle savedInstanceState) {}

    /**
     * This method gets called when the view saves its instance state.
     * With the MVVM pattern, the ViewModel is responsible for managing state.
     * Therefor the ViewModel is responsible to save the state appropriately.
     *
     * This method gets called on {@link BaseActivity#onSaveInstanceState(Bundle)}
     * @param outState Bundle in which to place your saved state.
     */
    protected void onSaveInstanceState(Bundle outState) {}

    /**
     * @return the attached view
     */
    public V getView() {
        return view;
    }
}
