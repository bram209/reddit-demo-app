package com.example.bram.reddit.redditfeed.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bram.reddit.R;
import com.example.bram.reddit.api.model.RedditPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bram on 2/21/17.
 */

public class RedditFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_DATA = 1;

    private final List<RedditPost> posts = new ArrayList<>();

    public RedditFeedAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= posts.size()) {
            return VIEW_TYPE_LOADING;
        }

        return VIEW_TYPE_DATA;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DATA) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.reddit_post_item, parent, false);
            return new PostViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loading_indicator, parent, false);
        return new LoadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PostViewHolder) {
            PostViewHolder postViewHolder = (PostViewHolder) holder;
            RedditPost redditPost = posts.get(position);
            postViewHolder.getViewModel().update(redditPost);
            postViewHolder.executePendingBindings();
        } else {
            ((LoadingViewHolder) holder).loadingIndicator.show();
        }
    }

    @Override
    public int getItemCount() {
        return posts.size() + 1;
    }

    public void addPosts(List<RedditPost> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    public List<RedditPost> getPosts() {
        return posts;
    }
}
