package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.media.Album;
import me.everything.providers.android.media.MediaProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;

/**
 * Created by sromku
 */
public class AlbumsFragment extends MediaFragment<Album> {

    @Override
    protected String getTitle() {
        return "Albums";
    }

    @Override
    protected void bindEntity(Album album, TextView title, TextView details) {
        title.setText(album.album);
        details.setText(album.artist);
    }

    @Override
    protected GetCursorTask.DataFetcher<Album> getFetcher() {
        return new GetCursorTask.DataFetcher<Album>() {
            @Override
            public Data<Album> getData() {
                MediaProvider provider = new MediaProvider(getApplicationContext());
                return provider.getAlbums(isExternal ?
                        MediaProvider.Storage.EXTERNAL :
                        MediaProvider.Storage.INTERNAL);
            }
        };
    }


}
