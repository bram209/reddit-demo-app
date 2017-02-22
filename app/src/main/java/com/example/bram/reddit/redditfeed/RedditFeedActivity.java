package com.example.bram.reddit.redditfeed;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bram.reddit.R;
import com.example.bram.reddit.databinding.ActivityRedditFeedBinding;
import com.example.bram.reddit.lib.BaseActivity;
import com.example.bram.reddit.lib.RequiresViewModel;

@RequiresViewModel(RedditFeedViewModel.class)
public class RedditFeedActivity extends BaseActivity<ActivityRedditFeedBinding, RedditFeedViewModel> implements RedditFeedView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_reddit_feed);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvRedditPostList.setLayoutManager(layoutManager);

        binding.rvRedditPostList.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (viewModel != null) {
                    viewModel.loadRedditFeed();
                }
            }
        });
    }

    @Override
    public void loadingFailed() {
        Toast.makeText(this, "Loading posts failed!", Toast.LENGTH_LONG).show();
    }
}
