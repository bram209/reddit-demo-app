package com.example.bram.reddit.redditfeed.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bram.reddit.R;
import com.example.bram.reddit.api.model.RedditPost;
import com.example.bram.reddit.lib.BaseViewHolder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by bram on 2/22/17.
 */
public class PostViewHolder extends BaseViewHolder<RedditPost> {
    @BindView(R.id.tv_post_author) TextView authorTextView;
    @BindView(R.id.tv_post_description) TextView descriptionTextView;
    @BindView(R.id.iv_post_thumbnail) ImageView tumbnailImageView;
    @BindView(R.id.tv_post_created) TextView createdTextView;
    @BindView(R.id.tv_post_comments_number) TextView commentsNumberTextView;
    
    public PostViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void update(RedditPost model) {
        authorTextView.setText(model.getAuthor());
        descriptionTextView.setText(model.getTitle());
        createdTextView.setText(model.getCreatedString());
        commentsNumberTextView.setText(model.getNumCommentsString());

        Picasso.with(tumbnailImageView.getContext())
                .load(model.getThumbnail())
                .into(tumbnailImageView);
    }
}
