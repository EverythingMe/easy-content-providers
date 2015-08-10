package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.media.MediaProvider;
import me.everything.providers.android.media.Playlist;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;

/**
 * Created by sromku
 */
public class PlaylistsFragment extends MediaFragment<Playlist> {

    @Override
    protected String getTitle() {
        return "Playlists";
    }

    @Override
    protected void bindEntity(Playlist playlist, TextView title, TextView details) {
        title.setText(playlist.name);
        details.setText(playlist.id + "");
    }

    @Override
    protected GetCursorTask.DataFetcher<Playlist> getFetcher() {
        return new GetCursorTask.DataFetcher<Playlist>() {
            @Override
            public Data<Playlist> getData() {
                MediaProvider provider = new MediaProvider(getApplicationContext());
                return provider.getPlaylists(isExternal ?
                        MediaProvider.Storage.EXTERNAL :
                        MediaProvider.Storage.INTERNAL);
            }
        };
    }


}
