package com.example.bram.reddit.redditfeed;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bram.reddit.R;
import com.example.bram.reddit.databinding.ActivityRedditFeedBinding;
import com.example.bram.reddit.lib.activity.BaseActivity;
import com.example.bram.reddit.lib.activity.RequiresActivityViewModel;

@RequiresActivityViewModel(RedditFeedViewModel.class)
public class RedditFeedActivity extends BaseActivity<ActivityRedditFeedBinding, RedditFeedViewModel> {

    private EndlessScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_reddit_feed);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvRedditPostList.setLayoutManager(layoutManager);
        scrollListener = new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (viewModel != null) {
                    viewModel.loadRedditFeed();
                }
            }
        };
        
        binding.rvRedditPostList.addOnScrollListener(scrollListener);
        viewModel.getLoadingFailed().doOnNext(this::onLoadFail); //bind to onLoadFail method
    }

    private void onLoadFail(Throwable throwable) {
        Toast.makeText(this, "Loading posts failed!", Toast.LENGTH_LONG).show();
        scrollListener.resetState();
    }
}
