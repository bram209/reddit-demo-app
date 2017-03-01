package com.example.bram.reddit.redditfeed;

import android.util.Log;

import com.example.bram.reddit.api.RedditService;
import com.example.bram.reddit.api.model.RedditFeed;
import com.example.bram.reddit.api.model.RedditPost;
import com.example.bram.reddit.lib.activity.ActivityViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by bram on 2/22/17.
 */

public class RedditFeedViewModel extends ActivityViewModel {

//    private static final String REDDIT_POSTS_KEY = "reddit_posts";

    private final PublishSubject<Throwable> loadingFailed = PublishSubject.create();
    private final PublishSubject<List<RedditPost>> redditPostsSubject = PublishSubject.create();
    
    private RedditFeed lastFeed;

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
                    Log.d("vm", "new posts");
                    redditPostsSubject.onNext(redditFeed.getPosts());
                })).subscribe();
    }

    public Observable<Throwable> getLoadingFailed() {
        return loadingFailed.hide();
    }
    
    public Observable<List<RedditPost>> getRedditPosts() {
        return redditPostsSubject;
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putParcelable(REDDIT_POSTS_KEY, Parcels.wrap());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        if (savedInstanceState != null && savedInstanceState.containsKey(REDDIT_POSTS_KEY)) {
//            ArrayList<RedditPost> list = Parcels.unwrap(savedInstanceState.getParcelable(REDDIT_POSTS_KEY));
//            adapter.addPosts(list);
//        }
//    }
}
