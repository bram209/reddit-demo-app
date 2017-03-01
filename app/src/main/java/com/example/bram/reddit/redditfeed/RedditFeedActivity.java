package com.example.bram.reddit.redditfeed;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bram.reddit.R;
import com.example.bram.reddit.api.model.RedditPost;
import com.example.bram.reddit.lib.activity.BaseActivity;
import com.example.bram.reddit.lib.activity.RequiresActivityViewModel;
import com.example.bram.reddit.redditfeed.recyclerview.RedditFeedAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresActivityViewModel(RedditFeedViewModel.class)
public class RedditFeedActivity extends BaseActivity<RedditFeedViewModel> {

    private EndlessScrollListener scrollListener;
    private RedditFeedAdapter adapter;

    @BindView(R.id.rv_reddit_post_list) RecyclerView redditPostList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_feed);
        ButterKnife.bind(this);

        adapter = new RedditFeedAdapter();
        redditPostList.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        redditPostList.setLayoutManager(layoutManager);

        scrollListener = new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (viewModel != null) {
                    viewModel.loadRedditFeed();
                }
            }
        };

        redditPostList.addOnScrollListener(scrollListener);

        initBindings();
    }

    private void initBindings() {
        viewModel.getLoadingFailed()
                .compose(bindToLifecycle())
                .doOnNext(this::onLoadFail)
                .subscribe();

        viewModel.getRedditPosts()
                .compose(bindToLifecycle())
                .doOnNext(this::addNewPosts)
                .subscribe();
    }

    private void addNewPosts(List<RedditPost> redditPosts) {
        adapter.addPosts(redditPosts);
    }

    private void onLoadFail(Throwable throwable) {
        Toast.makeText(this, "Loading posts failed!", Toast.LENGTH_LONG).show();
        scrollListener.resetState();
    }
}
