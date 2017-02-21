package com.example.bram.reddit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bram.reddit.R;
import com.example.bram.reddit.api.model.RedditPost;
import com.example.bram.reddit.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bram on 2/21/17.
 */

public class RedditPostAdapter extends RecyclerView.Adapter<RedditPostAdapter.ViewHolder> {
    private final List<RedditPost> postList = new ArrayList<>();
    private final Context context;

    public RedditPostAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reddit_post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RedditPost redditPost = postList.get(position);
        holder.authorTextView.setText(redditPost.getAuthor());
        holder.descriptionTextView.setText(redditPost.getTitle());
        holder.commentsNumberTextView.setText(redditPost.getNumComments() + " comments");
        holder.createdTextView.setText(Util.formatDate(redditPost.getCreated()));
        
        Picasso.with(context)
                .load(redditPost.getThumbnail())
                .into(holder.tumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void addPosts(List<RedditPost> posts){ 
        int startPos = posts.size()-1;
        postList.addAll(posts);
        notifyItemRangeChanged(startPos, posts.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_post_author) TextView authorTextView;
        @BindView(R.id.tv_post_description) TextView descriptionTextView;
        @BindView(R.id.iv_post_thumbnail) ImageView tumbnailImageView;
        @BindView(R.id.tv_post_comments_number) TextView commentsNumberTextView;
        @BindView(R.id.tv_post_created) TextView createdTextView;
        
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
