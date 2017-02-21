package com.example.bram.reddit.redditfeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.bram.reddit.R;
import com.example.bram.reddit.api.RedditService;
import com.example.bram.reddit.api.model.RedditFeed;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedditFeedActivity extends AppCompatActivity {

    @BindView(R.id.rv_reddit_post_list) RecyclerView redditPostsRecycleView;
    private RedditFeedAdapter redditPostsAdapter;
    private RedditFeed lastRedditFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_feed);

        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        redditPostsRecycleView.setLayoutManager(layoutManager);
        redditPostsAdapter = new RedditFeedAdapter(this);
        redditPostsRecycleView.setAdapter(redditPostsAdapter);

        redditPostsRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        redditPostsRecycleView.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (lastRedditFeed != null) {
                    loadRedditFeed(null, lastRedditFeed.getAfter());
                } else {
                    loadRedditFeed(null, null);
                }
            }
        });
    }

    private void loadRedditFeed(String before, String after) {
        RedditService.INSTANCE.getRedditApi().getTop(before, after).enqueue(new Callback<RedditFeed>() {
            @Override
            public void onResponse(Call<RedditFeed> call, Response<RedditFeed> response) {
                if (response.isSuccessful()) {
                    lastRedditFeed = response.body();
                    redditPostsAdapter.addPosts(lastRedditFeed.getPosts());
                    redditPostsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RedditFeed> call, Throwable t) {
                Log.d("Load failure", t.getMessage());
            }
        });
    }
}
