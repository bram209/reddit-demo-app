package com.example.bram.reddit.lib.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.example.bram.reddit.lib.ViewModelFactory;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by bram on 2/21/17.
 */

public abstract class BaseActivity<V extends ActivityViewModel> extends RxAppCompatActivity implements ActivityView {

    protected V viewModel;

    @Override
    @CallSuper
    @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelFactory.createViewModelForActivity(this);
        viewModel.attachView(this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        viewModel.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        
        if (viewModel != null) {
            viewModel.onSaveInstanceState(outState);
            viewModel.detachView();
        }

        viewModel = null;
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();

        if (viewModel != null) {
            viewModel.detachView();
        }

        viewModel = null;
    }
}
