package com.example.bram.reddit.api;

import com.example.bram.reddit.api.model.RedditListing;
import com.example.bram.reddit.api.model.RedditListingDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                .registerTypeAdapter(RedditListing.class, new RedditListingDeserializer())
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