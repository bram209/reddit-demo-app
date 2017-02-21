package com.example.bram.reddit.api.model;

import java.util.List;

/**
 * Created by bram on 2/21/17.
 */

public class RedditFeed {
    private List<RedditPost> posts;
    private String before;
    private String after;

    public List<RedditPost> getPosts() {
        return posts;
    }

    public void setPosts(List<RedditPost> posts) {
        this.posts = posts;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
