package me.everything.providers.sample.fragments;

import android.os.Bundle;

import me.everything.providers.core.Entity;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public abstract class MediaFragment<T extends Entity> extends RecycleViewCursorFragment<T> {

    public static final int MEDIA_EXTERNAL = 1;

    protected boolean isExternal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        isExternal = arguments != null && arguments.getInt("param") == MEDIA_EXTERNAL;
    }
}
