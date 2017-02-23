package com.example.bram.reddit.lib;

import android.databinding.ViewDataBinding;

/**
 * Created by bram on 2/22/17.
 */

public class ViewModelFactory {

    @SuppressWarnings("unchecked")
    public static <V extends ActivityViewModel> V createViewModelForActivity(ActivityView view) {
        final RequiresViewModel annotation = view.getClass().getAnnotation(RequiresViewModel.class);
        if (annotation == null) {
            throw new IllegalStateException(String.format("%s does not have the RequiresViewModel annotation!", view.getClass().getSimpleName()));
        }

        final Class<V> viewModelClass = (Class<V>) annotation.value();

        try {
            return viewModelClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
