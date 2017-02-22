package com.example.bram.reddit.redditfeed.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bram.reddit.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by bram on 2/22/17.
 */
public class LoadingViewHolder extends RecyclerView.ViewHolder {
    public AVLoadingIndicatorView loadingIndicator;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        loadingIndicator = (AVLoadingIndicatorView) itemView.findViewById(R.id.loading_indicator_view);
    }

}
