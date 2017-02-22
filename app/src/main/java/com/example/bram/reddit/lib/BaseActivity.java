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
        loadViewModel(savedInstanceState);
        viewModel.attachView(this, savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private void loadViewModel(Bundle savedInstanceState) {
        final RequiresViewModel annotation = getClass().getAnnotation(RequiresViewModel.class);
        if (annotation == null) {
            throw new IllegalStateException(String.format("%s does not have the RequiresViewModel annotation!", getClass().getSimpleName()));
        }

        final Class<V> viewModelClass = (Class<V>) annotation.value();
        viewModel = ViewModelManager.INSTANCE.get(this, viewModelClass, savedInstanceState);
    }

    @CallSuper
    public void setAndBindContentView(@LayoutRes int layoutResId) {
        binding = DataBindingUtil.setContentView(this, layoutResId);
        binding.setVariable(BR.vm, viewModel);
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (viewModel != null) {
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
