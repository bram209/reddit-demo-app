package com.example.bram.reddit.api.model;

import com.example.bram.reddit.util.Util;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class RedditPost {
    private String subreddit;
    private String title;
    private String author;
    private int points;
    @SerializedName("num_comments")
    private int numComments;
    private String url;
    private long created;
    private String thumbnail;
    private String description;

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getNumCommentsString() {
        return numComments + " comments";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getCreatedString() {
        return Util.formatDate(created);
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }
}