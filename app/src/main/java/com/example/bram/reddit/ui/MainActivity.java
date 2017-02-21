package com.example.bram.reddit.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.bram.reddit.R;
import com.example.bram.reddit.adapter.RedditPostAdapter;
import com.example.bram.reddit.api.RedditService;
import com.example.bram.reddit.api.model.RedditListing;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_reddit_post_list) RecyclerView redditPostsRecycleView;
    private RedditPostAdapter redditPostsAdapter;
    private RedditListing lastRedditListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        redditPostsRecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        redditPostsRecycleView.setLayoutManager(layoutManager);
        redditPostsAdapter = new RedditPostAdapter(this);
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
                if (lastRedditListing != null) {
                    loadRedditPosts(null, lastRedditListing.getAfter());
                } else {
                    loadRedditPosts(null,null);
                }
            }
        });
    } 

    private void loadRedditPosts(String before, String after) {
        RedditService.INSTANCE.getRedditApi().getTop(null, null).enqueue(new Callback<RedditListing>() {
            @Override
            public void onResponse(Call<RedditListing> call, Response<RedditListing> response) {
                if (response.isSuccessful()) {
                    lastRedditListing = response.body();
                    redditPostsAdapter.addPosts(lastRedditListing.getPosts());
                    redditPostsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RedditListing> call, Throwable t) {
                Log.d("Load failure", t.getMessage());
            }
        });
    }
}
