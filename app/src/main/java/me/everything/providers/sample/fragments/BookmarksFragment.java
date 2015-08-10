package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.browser.Bookmark;
import me.everything.providers.android.browser.BrowserProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public class BookmarksFragment extends RecycleViewCursorFragment<Bookmark> {

    @Override
    protected String getTitle() {
        return "Bookmarks";
    }

    @Override
    protected void bindEntity(Bookmark bookmark, TextView title, TextView details) {
        title.setText(bookmark.title);
        details.setText("Visits: " + bookmark.visits);
    }

    @Override
    protected GetCursorTask.DataFetcher<Bookmark> getFetcher() {
        return new GetCursorTask.DataFetcher<Bookmark>() {
            @Override
            public Data<Bookmark> getData() {
                BrowserProvider provider = new BrowserProvider(getApplicationContext());
                return provider.getBookmarks();
            }
        };
    }


}
