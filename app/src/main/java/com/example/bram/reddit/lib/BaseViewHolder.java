package com.example.bram.reddit.lib;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bram.reddit.BR;

/**
 * Created by bram on 2/22/17.
 */

public abstract class BaseViewHolder<B extends ViewDataBinding, V extends ViewModel> extends RecyclerView.ViewHolder {

    protected B binding;
    protected V viewModel;

    @SuppressWarnings("unchecked")
    public BaseViewHolder(View itemView) {
        super(itemView);
        viewModel = createViewModel();
        binding = DataBindingUtil.bind(itemView);
        binding.setVariable(BR.vm, viewModel);
    }

    protected abstract V createViewModel();

    public V getViewModel() {
        return viewModel;
    }

    public void executePendingBindings() {
        binding.executePendingBindings();
    }
}
