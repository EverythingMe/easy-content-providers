package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.browser.BrowserProvider;
import me.everything.providers.android.browser.Search;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public class SearchesFragment extends RecycleViewCursorFragment<Search> {

    @Override
    protected String getTitle() {
        return "Searches";
    }

    @Override
    protected void bindEntity(Search search, TextView title, TextView details) {
        title.setText(search.search);
        details.setText(search.date + "");
    }

    @Override
    protected GetCursorTask.DataFetcher<Search> getFetcher() {
        return new GetCursorTask.DataFetcher<Search>() {
            @Override
            public Data<Search> getData() {
                BrowserProvider provider = new BrowserProvider(getApplicationContext());
                return provider.getSearches();
            }
        };
    }


}
