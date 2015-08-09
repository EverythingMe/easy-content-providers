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
public class Audio extends Entity {

    @IgnoreMapping
    public static Uri uriExternal = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    @IgnoreMapping
    public static Uri uriInternal = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = MediaStore.MediaColumns.DATA, physicalType = FieldMapping.PhysicalType.Blob)
    public byte[] data;

    @FieldMapping(columnName = MediaStore.MediaColumns.SIZE, physicalType = FieldMapping.PhysicalType.Int)
    public int size;

    @FieldMapping(columnName = MediaStore.MediaColumns.DISPLAY_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String displayName;

    @FieldMapping(columnName = MediaStore.MediaColumns.TITLE, physicalType = FieldMapping.PhysicalType.String)
    public String title;

    @FieldMapping(columnName = MediaStore.MediaColumns.DATE_ADDED, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Long)
    public long dateAdded;

    @FieldMapping(columnName = MediaStore.MediaColumns.DATE_MODIFIED, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Long)
    public long dateModified;

    @FieldMapping(columnName = MediaStore.MediaColumns.MIME_TYPE, physicalType = FieldMapping.PhysicalType.String)
    public String mimeType;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.TITLE_KEY, physicalType = FieldMapping.PhysicalType.String)
    public String titleKey;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.DURATION, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Long)
    public long duration;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.BOOKMARK, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Long)
    public long bookmark;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.ARTIST_ID, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Long)
    public long artistId;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.ARTIST, physicalType = FieldMapping.PhysicalType.String)
    public String artist;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.ARTIST_KEY, physicalType = FieldMapping.PhysicalType.String)
    public String artistKey;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.COMPOSER, physicalType = FieldMapping.PhysicalType.String)
    public String composer;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.ALBUM_ID, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Long)
    public long albumId;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.ALBUM, physicalType = FieldMapping.PhysicalType.String)
    public String album;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.ALBUM_KEY, physicalType = FieldMapping.PhysicalType.String)
    public String albumKey;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.TRACK, physicalType = FieldMapping.PhysicalType.Int)
    public int track;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.YEAR, physicalType = FieldMapping.PhysicalType.Int)
    public int year;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.IS_MUSIC, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean isMusic;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.IS_PODCAST, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean isPodcast;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.IS_RINGTONE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean isRingtone;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.IS_ALARM, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean isAlarm;

    @FieldMapping(columnName = MediaStore.Audio.AudioColumns.IS_NOTIFICATION, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean isNotification;

}
