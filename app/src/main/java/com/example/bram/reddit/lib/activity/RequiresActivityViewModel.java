package com.example.bram.reddit.lib.activity;

import com.example.bram.reddit.lib.ViewModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used for instantiating view models dynamically through reflection.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresActivityViewModel {
    Class<? extends ActivityViewModel> value();
}
