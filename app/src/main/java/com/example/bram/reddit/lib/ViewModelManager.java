package com.example.bram.reddit.lib;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by bram on 2/22/17.
 */

public enum ViewModelManager {
    INSTANCE;

    private static final String VIEW_MODEL_ID_KEY = "view_model_id";

    private final Map<String, ViewModel> cache = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T get(@NonNull Context context, @NonNull Class<T> viewModelClass, @Nullable Bundle savedInstanceState) {
        String id;
        if (savedInstanceState == null) {
            id = UUID.randomUUID().toString();
        } else {
            id = savedInstanceState.getString(VIEW_MODEL_ID_KEY);
        }

        ViewModel viewModel = cache.get(id);
        if (viewModel != null) {
            return (T) viewModel;
        }

        try {
            return (T) viewModelClass.getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
