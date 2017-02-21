package com.example.bram.reddit.api;

import com.example.bram.reddit.api.model.RedditFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bram on 2/21/17.
 */

public interface RedditApi {
    @GET("/top.json")
    Call<RedditFeed> getTop(@Query("after") String after,
                            @Query("limit") String limit);

}
