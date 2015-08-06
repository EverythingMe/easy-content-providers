package me.everything.providers.android.browser;

import android.content.Context;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

public class BrowserProvider extends AbstractProvider {

    public BrowserProvider(Context context) {
        super(context);
    }

    public Data<Bookmark> getBookmarks() {
        Data<Bookmark> bookmarks = getContentTableData(Bookmark.uri, Bookmark.class);
        return bookmarks;
    }

    public Data<Search> getSearches() {
        Data<Search> searches = getContentTableData(Search.uri, Search.class);
        return searches;
    }

}
