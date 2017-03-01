package com.example.bram.reddit.redditfeed.recyclerview;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.bram.reddit.api.model.RedditPost;
import com.example.bram.reddit.lib.ViewHolderViewModel;
import com.example.bram.reddit.lib.ViewModel;
import com.squareup.picasso.Picasso;

/**
 * Created by bram on 2/22/17.
 */

public class PostViewModel extends ViewHolderViewModel<RedditPost> {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

}
