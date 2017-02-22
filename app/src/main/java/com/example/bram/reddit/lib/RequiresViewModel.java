package com.example.bram.reddit.lib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by bram on 2/22/17.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresViewModel {
    Class<? extends ViewModel> value();
}
