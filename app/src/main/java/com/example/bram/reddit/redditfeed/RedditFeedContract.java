package com.example.bram.reddit.redditfeed;

import com.example.bram.reddit.lib.BasePresenter;
import com.example.bram.reddit.lib.BaseView;
import com.example.bram.reddit.api.model.RedditPost;

import java.util.List;

/**
 * Created by bram on 2/21/17.
 */

public class RedditFeedContract {
    
    interface View extends BaseView<Presenter> {
        
        void addRedditPosts(List<RedditPost> posts);
    }
    
    interface Presenter extends BasePresenter {
        
        void loadRedditFeed();
    }
}
