package me.everything.providers.android.media;

import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Created by sromku
 */
public class Genre extends Entity {

    @IgnoreMapping
    public static Uri uriExternal = MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI;

    @IgnoreMapping
    public static Uri uriInternal = MediaStore.Audio.Genres.INTERNAL_CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = MediaStore.Audio.GenresColumns.NAME, physicalType = FieldMapping.PhysicalType.String)
    public String name;

}
