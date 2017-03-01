package com.example.bram.reddit.lib;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by bram on 2/22/17.
 */

public abstract class BaseViewHolder< M> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    
    public abstract void update(M model);
}