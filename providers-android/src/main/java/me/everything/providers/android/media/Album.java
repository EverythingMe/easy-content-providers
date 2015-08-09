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
public class Album extends Entity {

    @IgnoreMapping
    public static Uri uriExternal = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    @IgnoreMapping
    public static Uri uriInternal = MediaStore.Audio.Albums.INTERNAL_CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = MediaStore.Audio.AlbumColumns.ALBUM, physicalType = FieldMapping.PhysicalType.String)
    public String album;

    @FieldMapping(columnName = MediaStore.Audio.AlbumColumns.ARTIST, physicalType = FieldMapping.PhysicalType.String)
    public String artist;

    @FieldMapping(columnName = MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS, physicalType = FieldMapping.PhysicalType.Int)
    public int numOfSongs;

    @FieldMapping(columnName = MediaStore.Audio.AlbumColumns.FIRST_YEAR, physicalType = FieldMapping.PhysicalType.Int)
    public int firstYear;

    @FieldMapping(columnName = MediaStore.Audio.AlbumColumns.LAST_YEAR, physicalType = FieldMapping.PhysicalType.Int)
    public int lastYear;

    @FieldMapping(columnName = MediaStore.Audio.AlbumColumns.ALBUM_KEY, physicalType = FieldMapping.PhysicalType.String)
    public String albumKey;

    @FieldMapping(columnName = MediaStore.Audio.AlbumColumns.ALBUM_ART, physicalType = FieldMapping.PhysicalType.String)
    public String albumArt;

}
