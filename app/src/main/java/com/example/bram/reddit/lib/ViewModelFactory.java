package com.example.bram.reddit.lib;

import com.example.bram.reddit.lib.activity.ActivityView;
import com.example.bram.reddit.lib.activity.ActivityViewModel;
import com.example.bram.reddit.lib.activity.RequiresActivityViewModel;

/**
 * Created by bram on 2/22/17.
 */

public class ViewModelFactory {

    @SuppressWarnings("unchecked")
    public static <V extends ActivityViewModel> V createViewModelForActivity(ActivityView view) {
        final RequiresActivityViewModel annotation = view.getClass().getAnnotation(RequiresActivityViewModel.class);
        if (annotation == null) {
            throw new IllegalStateException(String.format("%s does not have the RequiresActivityViewModel annotation!", view.getClass().getSimpleName()));
        }

        final Class<V> viewModelClass = (Class<V>) annotation.value();

        try {
            return viewModelClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
