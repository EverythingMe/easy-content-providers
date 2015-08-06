package me.everything.providers.sample.fragments;

import android.widget.TextView;

import java.text.SimpleDateFormat;

import me.everything.providers.android.browser.BrowserProvider;
import me.everything.providers.android.browser.Search;
import me.everything.providers.core.Data;
import me.everything.providers.sample.GetCursorTask;

/**
 * Created by sromku.
 */
public class SearchFragment extends RecycleViewCursorFragment<Search> {

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");

    @Override
    protected String getTitle() {
        return "Browser Searches";
    }

    @Override
    protected void bindEntity(Search search, TextView title, TextView details) {
        title.setText(search.search);
        details.setText(String.valueOf(mDateFormat.format(search.date)));
    }

    @Override
    protected GetCursorTask.DataFetcher<Search> getFetcher() {
        return new GetCursorTask.DataFetcher<Search>() {
            @Override
            public Data<Search> getData() {
                BrowserProvider browserProvider = new BrowserProvider(getApplicationContext());
                return browserProvider.getSearches();
            }
        };
    }


}
