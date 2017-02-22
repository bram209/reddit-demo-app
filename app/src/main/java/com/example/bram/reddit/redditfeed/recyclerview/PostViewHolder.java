package com.example.bram.reddit.redditfeed.recyclerview;

import android.view.View;

import com.example.bram.reddit.databinding.RedditPostItemBinding;
import com.example.bram.reddit.lib.BaseViewHolder;

;

/**
 * Created by bram on 2/22/17.
 */
public class PostViewHolder extends BaseViewHolder<RedditPostItemBinding, PostViewModel> {
    public PostViewHolder(View itemView) {
        super(itemView);
    }
    
    @Override
    protected PostViewModel createViewModel() {
        return new PostViewModel();
    }
}
