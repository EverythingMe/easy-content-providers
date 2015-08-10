package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.media.Genre;
import me.everything.providers.android.media.MediaProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;

/**
 * Created by sromku
 */
public class GenresFragment extends MediaFragment<Genre> {

    @Override
    protected String getTitle() {
        return "Genres";
    }

    @Override
    protected void bindEntity(Genre genre, TextView title, TextView details) {
        title.setText(genre.name);
        details.setText(genre.id + "");
    }

    @Override
    protected GetCursorTask.DataFetcher<Genre> getFetcher() {
        return new GetCursorTask.DataFetcher<Genre>() {
            @Override
            public Data<Genre> getData() {
                MediaProvider provider = new MediaProvider(getApplicationContext());
                return provider.getGenres(isExternal ?
                        MediaProvider.Storage.EXTERNAL :
                        MediaProvider.Storage.INTERNAL);
            }
        };
    }


}
