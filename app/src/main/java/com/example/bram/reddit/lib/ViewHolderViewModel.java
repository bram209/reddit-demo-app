package com.example.bram.reddit.lib;

/**
 * Created by bram on 3/1/17.
 */
public interface ViewHolderViewModel<M> extends ViewModel {
    
    void update(M model);
}
