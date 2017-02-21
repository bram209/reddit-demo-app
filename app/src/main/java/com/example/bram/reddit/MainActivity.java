package com.example.bram.reddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        
//        redditPostsRecycleView.setHasFixedSize(true);
        redditPostsRecycleView.setLayoutManager(new LinearLayoutManager(this));
        redditPostsAdapter = new RedditPostAdapter(this);
        redditPostsRecycleView.setAdapter(redditPostsAdapter);
        
        RedditService.INSTANCE.getRedditApi().getTop(null, null).enqueue(new Callback<RedditListing>() {
            @Override
            public void onResponse(Call<RedditListing> call, Response<RedditListing> response) {
                if (response.isSuccessful()) {
                    redditPostsAdapter.addPosts(response.body().getPosts());
                    redditPostsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RedditListing> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
