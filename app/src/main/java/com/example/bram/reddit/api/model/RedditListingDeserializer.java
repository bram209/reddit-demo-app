package com.example.bram.reddit.api.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bram on 2/21/17.
 */

public class RedditListingDeserializer implements JsonDeserializer<RedditListing> {
    @Override
    public RedditListing deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        RedditListing redditListing = new RedditListing();
        JsonObject obj = json.getAsJsonObject();
        JsonObject data = obj.getAsJsonObject("data");
        redditListing.setAfter(data.get("after").getAsString());
        JsonElement beforeElement = data.get("before");
        if (!beforeElement.isJsonNull()) {
            redditListing.setBefore(beforeElement.getAsString());
        }
        
        List<RedditPost> posts = new ArrayList<>();
        JsonArray postArray = data.getAsJsonArray("children");
        Gson gson = new Gson();
        for (JsonElement jsonPost : postArray) {
            RedditPost redditPost = gson.fromJson(jsonPost.getAsJsonObject().get("data"), RedditPost.class);
            posts.add(redditPost);
        }
        
        redditListing.setPosts(posts);
        return redditListing;
    }
}