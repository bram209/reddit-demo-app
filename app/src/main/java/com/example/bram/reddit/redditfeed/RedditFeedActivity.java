package com.example.bram.reddit.redditfeed;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bram.reddit.R;
import com.example.bram.reddit.databinding.ActivityRedditFeedBinding;
import com.example.bram.reddit.lib.BaseActivity;
import com.example.bram.reddit.lib.RequiresViewModel;

@RequiresViewModel(RedditFeedViewModel.class)
public class RedditFeedActivity extends BaseActivity<ActivityRedditFeedBinding, RedditFeedViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_reddit_feed);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvRedditPostList.setLayoutManager(layoutManager);

        binding.rvRedditPostList.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                viewModel.loadRedditFeed();
            }
        });
    }
}
