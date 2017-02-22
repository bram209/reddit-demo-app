package com.example.bram.reddit.redditfeed;

import android.os.Bundle;
import android.util.Log;

import com.example.bram.reddit.api.RedditService;
import com.example.bram.reddit.api.model.RedditFeed;
import com.example.bram.reddit.api.model.RedditPost;
import com.example.bram.reddit.lib.ViewModel;
import com.example.bram.reddit.redditfeed.recyclerview.RedditFeedAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bram on 2/22/17.
 */

public class RedditFeedViewModel extends ViewModel<RedditFeedActivity> {

    private static final String REDDIT_POSTS_KEY = "reddit_posts";
    
    private RedditFeedAdapter adapter = new RedditFeedAdapter();
    private RedditFeed lastFeed;

    public RedditFeedAdapter getAdapter() {
        return adapter;
    }

    public void loadRedditFeed() {
        if (lastFeed == null) {
            loadRedditFeed(null, 35);
        } else {
            loadRedditFeed(lastFeed.getAfter(), 25);
        }
    }

    private void loadRedditFeed(String after, int amount) {
        RedditService.INSTANCE.getRedditApi().getTop(after, amount).enqueue(new Callback<RedditFeed>() {
            @Override
            public void onResponse(Call<RedditFeed> call, Response<RedditFeed> response) {
                if (response.isSuccessful()) {
                    RedditFeed feed = response.body();
                    lastFeed = feed;
                    adapter.addPosts(feed.getPosts());
                }
            }

            @Override
            public void onFailure(Call<RedditFeed> call, Throwable t) {
                Log.d("Load failure", t.getMessage());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(REDDIT_POSTS_KEY, Parcels.wrap(adapter.getPosts()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(REDDIT_POSTS_KEY)) {
            ArrayList<RedditPost> list = savedInstanceState.getParcelable(REDDIT_POSTS_KEY);
            adapter.addPosts(list);
        }
    }
}
