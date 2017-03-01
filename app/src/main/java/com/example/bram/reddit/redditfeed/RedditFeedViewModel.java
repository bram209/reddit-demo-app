package com.example.bram.reddit.redditfeed;

import android.os.Bundle;

import com.example.bram.reddit.api.RedditService;
import com.example.bram.reddit.api.model.RedditFeed;
import com.example.bram.reddit.api.model.RedditPost;
import com.example.bram.reddit.lib.activity.ActivityViewModel;
import com.example.bram.reddit.redditfeed.recyclerview.RedditFeedAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by bram on 2/22/17.
 */

public class RedditFeedViewModel extends ActivityViewModel {

    private static final String REDDIT_POSTS_KEY = "reddit_posts";

    private final PublishSubject<Throwable> loadingFailed;
    private final RedditFeedAdapter adapter;
    private RedditFeed lastFeed;

    public RedditFeedViewModel() {
        loadingFailed = PublishSubject.create();
        adapter = new RedditFeedAdapter();
    }

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
        RedditService.INSTANCE.getRedditApi().getTop(after, amount)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(loadingFailed::onNext)
                .doOnNext((redditFeed -> {
                    lastFeed = redditFeed;
                    adapter.addPosts(redditFeed.getPosts());
                })).subscribe();
    }

    public Observable<Throwable> getLoadingFailed() {
        return loadingFailed;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(REDDIT_POSTS_KEY, Parcels.wrap(adapter.getPosts()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(REDDIT_POSTS_KEY)) {
            ArrayList<RedditPost> list = Parcels.unwrap(savedInstanceState.getParcelable(REDDIT_POSTS_KEY));
            adapter.addPosts(list);
        }
    }
}
