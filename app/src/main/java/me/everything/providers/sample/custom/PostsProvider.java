package me.everything.providers.sample.custom;

import android.content.Context;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

public class PostsProvider extends AbstractProvider {

    protected PostsProvider(Context context) {
        super(context);
    }

    /**
     * Get all posts
     */
    public Data<Post> getPosts() {
        Data<Post> posts = getContentTableData(Post.uri, Post.class);
        return posts;
    }
}
