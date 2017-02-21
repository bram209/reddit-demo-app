package com.example.bram.reddit.api;

import com.example.bram.reddit.api.model.RedditFeed;
import com.example.bram.reddit.api.model.RedditListingDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bram on 2/21/17.
 */

public enum RedditService {
    INSTANCE;

    private static final String BASE_URL = "https://www.reddit.com";
    private RedditApi redditApi;

    RedditService() {
        init();
    }

    private void init() {
        OkHttpClient httpClient = createHttpClient();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(RedditFeed.class, new RedditListingDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        redditApi = retrofit.create(RedditApi.class);
    }

    private OkHttpClient createHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    public RedditApi getRedditApi() {
        return redditApi;
    }
}