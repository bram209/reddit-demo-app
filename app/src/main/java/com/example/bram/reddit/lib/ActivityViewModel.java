package com.example.bram.reddit.lib;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by bram on 2/23/17.
 */

public class ActivityViewModel extends ViewModel {
    private ActivityView view;

    /**
     * The transformer should be composed when creating a new observable in the view model.
     * This way the subscriptions will be unsubscribed appropriately during the activity's lifecycle
     *
     * @return a reusable {@link LifecycleTransformer} that unsubscribes the source during the activity lifecycle
     */
    protected <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(getView().lifecycle());
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
     * This method gets called when a new view is attached
     * @param view The view that is attached
     */
    @CallSuper
    public void attachView(ActivityView view) {
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
     * @return the attached view
     */
    public ActivityView getView() {
        return view;
    }
}
