package com.example.bram.reddit.lib;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.bram.reddit.BR;

/**
 * Created by bram on 2/21/17.
 */

public abstract class BaseActivity<B extends ViewDataBinding, V extends ViewModel> extends AppCompatActivity implements MyView {

    protected B binding;
    protected V viewModel;

    @Override
    @CallSuper
    @SuppressWarnings("unchecked")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelFactory.createViewModelForView(this);
        viewModel.attachView(this);
    }
    
    @CallSuper
    public void setAndBindContentView(@LayoutRes int layoutResId) {
        binding = DataBindingUtil.setContentView(this, layoutResId);
        binding.setVariable(BR.vm, viewModel);
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

        binding = null;
        viewModel = null;
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();

        if (viewModel != null) {
            viewModel.detachView();
        }

        binding = null;
        viewModel = null;
    }
}
