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

    public enum Storage {
        INTERNAL,
        EXTERNAL
    }
}
