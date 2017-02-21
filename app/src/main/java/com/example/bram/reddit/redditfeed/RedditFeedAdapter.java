package com.example.bram.reddit.redditfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bram.reddit.R;
import com.example.bram.reddit.api.model.RedditPost;
import com.example.bram.reddit.util.Util;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bram on 2/21/17.
 */

public class RedditFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_DATA = 1;

    private final List<RedditPost> posts = new ArrayList<>();
    private final Context context;

    public RedditFeedAdapter(Context context) {
        this.context = context;
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
            postViewHolder.authorTextView.setText(redditPost.getAuthor());
            postViewHolder.descriptionTextView.setText(redditPost.getTitle());
            postViewHolder.commentsNumberTextView.setText(redditPost.getNumComments() + " comments");
            postViewHolder.createdTextView.setText(Util.formatDate(redditPost.getCreated()));

            Picasso.with(context)
                    .load(redditPost.getThumbnail())
                    .into(postViewHolder.tumbnailImageView);
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

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.loading_indicator_view) AVLoadingIndicatorView loadingIndicator;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_post_author) TextView authorTextView;
        @BindView(R.id.tv_post_description) TextView descriptionTextView;
        @BindView(R.id.iv_post_thumbnail) ImageView tumbnailImageView;
        @BindView(R.id.tv_post_comments_number) TextView commentsNumberTextView;
        @BindView(R.id.tv_post_created) TextView createdTextView;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
