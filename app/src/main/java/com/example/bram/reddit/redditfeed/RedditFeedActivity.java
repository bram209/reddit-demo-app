package com.example.bram.reddit.redditfeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.bram.reddit.R;
import com.example.bram.reddit.api.RedditService;
import com.example.bram.reddit.api.model.RedditFeed;
import com.example.bram.reddit.api.model.RedditPost;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedditFeedActivity extends AppCompatActivity implements RedditFeedContract.View {

    @BindView(R.id.rv_reddit_post_list) RecyclerView redditPostsRecycleView;
    
    private RedditFeedAdapter redditPostsAdapter;
    private RedditFeedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_feed);

        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        redditPostsRecycleView.setLayoutManager(layoutManager);
        redditPostsAdapter = new RedditFeedAdapter(this);
        redditPostsRecycleView.setAdapter(redditPostsAdapter);
        
        presenter = new RedditFeedPresenter(this);
        redditPostsRecycleView.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.loadRedditFeed();
            }
        });
    }

    @Override
    public void addRedditPosts(List<RedditPost> posts) {
        redditPostsAdapter.addPosts(posts);
    }

    @Override
    public void setPresenter(RedditFeedContract.Presenter presenter) {

    }
}
