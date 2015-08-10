package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.media.Image;
import me.everything.providers.android.media.MediaProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;

/**
 * Created by sromku
 */
public class ImagesFragment extends MediaFragment<Image> {

    @Override
    protected String getTitle() {
        return "Images";
    }

    @Override
    protected void bindEntity(Image image, TextView title, TextView details) {
        title.setText(image.title);
        details.setText(image.mimeType);
    }

    @Override
    protected GetCursorTask.DataFetcher<Image> getFetcher() {
        return new GetCursorTask.DataFetcher<Image>() {
            @Override
            public Data<Image> getData() {
                MediaProvider provider = new MediaProvider(getApplicationContext());
                return provider.getImages( isExternal ?
                                MediaProvider.Storage.EXTERNAL :
                                MediaProvider.Storage.INTERNAL);
            }
        };
    }


}
