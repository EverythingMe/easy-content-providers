package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.media.MediaProvider;
import me.everything.providers.android.media.Video;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;

/**
 * Created by sromku
 */
public class VideosFragment extends MediaFragment<Video> {

    @Override
    protected String getTitle() {
        return "Videos";
    }

    @Override
    protected void bindEntity(Video video, TextView title, TextView details) {
        title.setText(video.title);
        details.setText(video.mimeType);
    }

    @Override
    protected GetCursorTask.DataFetcher<Video> getFetcher() {
        return new GetCursorTask.DataFetcher<Video>() {
            @Override
            public Data<Video> getData() {
                MediaProvider provider = new MediaProvider(getApplicationContext());
                return provider.getVideos(isExternal ?
                        MediaProvider.Storage.EXTERNAL :
                        MediaProvider.Storage.INTERNAL);
            }
        };
    }


}
