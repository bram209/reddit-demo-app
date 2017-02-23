package com.example.bram.reddit.redditfeed.recyclerview;

import android.view.View;

import com.example.bram.reddit.api.model.RedditFeed;
import com.example.bram.reddit.api.model.RedditPost;
import com.example.bram.reddit.databinding.RedditPostItemBinding;
import com.example.bram.reddit.lib.BaseViewHolder;
import com.example.bram.reddit.lib.ViewHolderViewModel;

/**
 * Created by bram on 2/22/17.
 */
public class PostViewHolder extends BaseViewHolder<RedditPostItemBinding, PostViewModel , RedditPost> {
    public PostViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public ViewHolderViewModel createViewModel() {
        return new PostViewModel();
    }

}
