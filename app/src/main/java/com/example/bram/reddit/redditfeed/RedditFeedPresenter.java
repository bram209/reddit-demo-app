package com.example.bram.reddit.redditfeed;

import android.util.Log;

import com.example.bram.reddit.api.RedditApi;
import com.example.bram.reddit.api.RedditService;
import com.example.bram.reddit.api.model.RedditFeed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bram on 2/21/17.
 */

public class RedditFeedPresenter implements RedditFeedContract.Presenter {

    private final RedditApi redditApi = RedditService.INSTANCE.getRedditApi();
    private final RedditFeedContract.View redditFeedView;

    private boolean firstLoad = true;
    private RedditFeed lastFeed;

    public RedditFeedPresenter(RedditFeedContract.View redditFeedView) {
        this.redditFeedView = redditFeedView;
        redditFeedView.setPresenter(this);
    }

    @Override
    public void loadRedditFeed() {
        if (firstLoad) {
            loadRedditFeed(null, null);
        } else {
            loadRedditFeed(null, lastFeed.getAfter());
        }

        firstLoad = false;
    }

    private void loadRedditFeed(String before, String after) {
        RedditService.INSTANCE.getRedditApi().getTop(before, after).enqueue(new Callback<RedditFeed>() {
            @Override
            public void onResponse(Call<RedditFeed> call, Response<RedditFeed> response) {
                if (response.isSuccessful()) {
                    RedditFeed feed = response.body();
                    lastFeed = feed;
                    redditFeedView.addRedditPosts(feed.getPosts());
                }
            }

            @Override
            public void onFailure(Call<RedditFeed> call, Throwable t) {
                Log.d("Load failure", t.getMessage());
            }
        });
    }

    @Override
    public void start() {

    }
}
