package com.example.bram.reddit.lib;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bram.reddit.BR;

/**
 * Created by bram on 2/22/17.
 */

public abstract class BaseViewHolder<B extends ViewDataBinding, V extends ViewHolderViewModel<M>, M> extends RecyclerView.ViewHolder {

    protected B binding;
    protected ViewHolderViewModel<M> viewModel;

    public BaseViewHolder(View itemView) {
        super(itemView);
        viewModel = createViewModel();
        binding = DataBindingUtil.bind(itemView);
        binding.setVariable(BR.vm, viewModel);
    }
    
    public abstract ViewHolderViewModel createViewModel();
    
    @CallSuper
    public void update(M model) {
        viewModel.update(model);
        binding.executePendingBindings();
    }
}