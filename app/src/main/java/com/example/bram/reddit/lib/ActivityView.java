package com.example.bram.reddit.lib;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observable;

/**
 * Created by bram on 2/23/17.
 */

public interface ActivityView {
    /**
     * This method is used to get access to the activity's lifecycle events.
     * 
     * @see {@link BaseActivity#lifecycle()}
     * @return lifecycle of the activity
     */
    Observable<ActivityEvent> lifecycle();
}
