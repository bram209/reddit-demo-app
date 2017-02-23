package com.example.bram.reddit.lib;

/**
 * Created by bram on 2/23/17.
 */
public abstract class ViewHolderViewModel<M> extends ViewModel {
    private M model;

    public M getModel() {
        return model;
    }

    void update(M model) {
        this.model = model;
        notifyChange();
    }
}
