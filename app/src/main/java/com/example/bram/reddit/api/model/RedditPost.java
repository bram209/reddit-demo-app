package com.example.bram.reddit.api.model;

import com.google.gson.annotations.SerializedName;

public class RedditPost {
    String subreddit;
    String title;
    String author;
    int points;
    
    @SerializedName("num_comments")
    int numComments;
    String url;
    long created;
    String thumbnail;
}