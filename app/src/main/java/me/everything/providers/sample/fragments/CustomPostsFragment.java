package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetEntitiesTask;
import me.everything.providers.sample.base.RecycleViewListFragment;
import me.everything.providers.sample.custom.Post;
import me.everything.providers.sample.custom.PostsProvider;

public class CustomPostsFragment extends RecycleViewListFragment<Post> {

    @Override
    protected String getTitle() {
        return "Custom - Posts";
    }

    @Override
    protected void bindEntity(Post post, TextView title, TextView details) {
        title.setText(post.title);
        details.setText("Id: " + post.id);
    }

    @Override
    protected GetEntitiesTask.DataFetcher<Post> getFetcher() {
        return new GetEntitiesTask.DataFetcher<Post>() {
            @Override
            public Data<Post> getData() {
                PostsProvider provider = new PostsProvider(getApplicationContext());
                return provider.getPosts();
            }
        };
    }

}
