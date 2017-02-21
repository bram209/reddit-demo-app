package com.example.bram.reddit.ui;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bram.reddit.R;
import com.example.bram.reddit.api.model.RedditPost;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bram on 2/21/17.
 */

public class RedditPostView extends RelativeLayout {

    private final RedditPost redditPost;

    @BindView(R.id.tv_post_author) TextView authorTextView;
    @BindView(R.id.tv_post_description) TextView descriptionTextView;
    @BindView(R.id.iv_post_thumbnail) ImageView tumbnailImageView;
    @BindView(R.id.tv_post_comments_number) TextView commentsNumberTextView;
    @BindView(R.id.tv_post_created) TextView createdTextView;

    public RedditPostView(Context context, RedditPost redditPost) {
        super(context);
        this.redditPost = redditPost;
        
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.reddit_post_item, this);
        ButterKnife.bind(this);
        
        authorTextView.setText(redditPost.getAuthor());
        descriptionTextView.setText(redditPost.getDescription());
        commentsNumberTextView.setText(String.valueOf(redditPost.getNumComments()));
        
        //TODO format creatred date
        //TODO tumbnail
    }


}
