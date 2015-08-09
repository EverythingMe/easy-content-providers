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
public class Artist extends Entity {

    @IgnoreMapping
    public static Uri uriExternal = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

    @IgnoreMapping
    public static Uri uriInternal = MediaStore.Audio.Artists.INTERNAL_CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = MediaStore.Audio.ArtistColumns.ARTIST, physicalType = FieldMapping.PhysicalType.String)
    public String artist;

    @FieldMapping(columnName = MediaStore.Audio.ArtistColumns.ARTIST_KEY, physicalType = FieldMapping.PhysicalType.String)
    public String artistKey;

    @FieldMapping(columnName = MediaStore.Audio.ArtistColumns.NUMBER_OF_ALBUMS, physicalType = FieldMapping.PhysicalType.Int)
    public int numOfAlbums;

    @FieldMapping(columnName = MediaStore.Audio.ArtistColumns.NUMBER_OF_TRACKS, physicalType = FieldMapping.PhysicalType.Int)
    public int numOfTracks;

}
