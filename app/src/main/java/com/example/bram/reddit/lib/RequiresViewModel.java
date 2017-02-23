package com.example.bram.reddit.lib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used for instantiating view models dynamically through reflection.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresViewModel {
    Class<? extends ViewModel> value();
}
