package me.everything.providers.android.media;

import android.content.Context;
import android.provider.MediaStore;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

/**
 * Created by sromku
 */
public class MediaProvider extends AbstractProvider {

    private final static int LIMIT = 250;
    private final static String ORDER_BY_COLUMN = MediaStore.MediaColumns.DATE_MODIFIED;

    public MediaProvider(Context context) {
        super(context);
    }

    public Data<File> getFiles(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(File.uriInternal, File.class);
            default:
                return getContentTableData(File.uriExternal, null, null,
                        ORDER_BY_COLUMN + " DESC" + " LIMIT " + LIMIT,
                        File.class);
        }
    }

    public Data<Image> getImages(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Image.uriInternal, Image.class);
            default:
                return getContentTableData(Image.uriExternal, null, null,
                        ORDER_BY_COLUMN + " DESC" + " LIMIT " + LIMIT,
                        Image.class);
        }
    }

    public Data<Video> getVideos(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Video.uriInternal, Video.class);
            default:
                return getContentTableData(Video.uriExternal, null, null,
                        ORDER_BY_COLUMN + " DESC" + " LIMIT " + LIMIT,
                        Video.class);
        }
    }

    public Data<Audio> getAudios(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Audio.uriInternal, Audio.class);
            default:
                return getContentTableData(Audio.uriExternal, null, null,
                        ORDER_BY_COLUMN + " DESC" + " LIMIT " + LIMIT,
                        Audio.class);
        }
    }

    public enum Storage {
        INTERNAL,
        EXTERNAL
    }
}
