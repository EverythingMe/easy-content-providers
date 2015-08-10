package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.media.Artist;
import me.everything.providers.android.media.MediaProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;

/**
 * Created by sromku
 */
public class ArtistsFragment extends MediaFragment<Artist> {

    @Override
    protected String getTitle() {
        return "Artists";
    }

    @Override
    protected void bindEntity(Artist artist, TextView title, TextView details) {
        title.setText(artist.artist);
        details.setText(artist.id + "");
    }

    @Override
    protected GetCursorTask.DataFetcher<Artist> getFetcher() {
        return new GetCursorTask.DataFetcher<Artist>() {
            @Override
            public Data<Artist> getData() {
                MediaProvider provider = new MediaProvider(getApplicationContext());
                return provider.getArtists(isExternal ?
                        MediaProvider.Storage.EXTERNAL :
                        MediaProvider.Storage.INTERNAL);
            }
        };
    }


}
