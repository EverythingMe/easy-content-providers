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
public class Video extends Entity {

    @IgnoreMapping
    public static Uri uriExternal = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

    @IgnoreMapping
    public static Uri uriInternal = MediaStore.Video.Media.INTERNAL_CONTENT_URI;

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

    @FieldMapping(columnName = MediaStore.MediaColumns.DATE_ADDED, physicalType = FieldMapping.PhysicalType.Long)
    public long dateAdded;

    @FieldMapping(columnName = MediaStore.MediaColumns.DATE_MODIFIED, physicalType = FieldMapping.PhysicalType.Long)
    public long dateModified;

    @FieldMapping(columnName = MediaStore.MediaColumns.MIME_TYPE, physicalType = FieldMapping.PhysicalType.String)
    public String mimeType;

    @FieldMapping(columnName = MediaStore.MediaColumns.WIDTH, physicalType = FieldMapping.PhysicalType.Int)
    public int width;

    @FieldMapping(columnName = MediaStore.MediaColumns.HEIGHT, physicalType = FieldMapping.PhysicalType.Int)
    public int height;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.DURATION, physicalType = FieldMapping.PhysicalType.Int)
    public int duration;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.ARTIST, physicalType = FieldMapping.PhysicalType.String)
    public String artist;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.ALBUM, physicalType = FieldMapping.PhysicalType.String)
    public String album;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.RESOLUTION, physicalType = FieldMapping.PhysicalType.String)
    public String resolution;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.DESCRIPTION, physicalType = FieldMapping.PhysicalType.String)
    public String description;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.IS_PRIVATE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean isPrivate;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.TAGS, physicalType = FieldMapping.PhysicalType.String)
    public String tags;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.CATEGORY, physicalType = FieldMapping.PhysicalType.String)
    public String category;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.LANGUAGE, physicalType = FieldMapping.PhysicalType.String)
    public String language;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.LATITUDE, physicalType = FieldMapping.PhysicalType.Double)
    public double latitude;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.LONGITUDE, physicalType = FieldMapping.PhysicalType.Double)
    public double longitude;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.DATE_TAKEN, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Long)
    public long dateTaken;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.MINI_THUMB_MAGIC, physicalType = FieldMapping.PhysicalType.Int)
    public int miniTthumbMagic;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.BUCKET_ID, physicalType = FieldMapping.PhysicalType.String)
    public String bucketId;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String bucketDisplayName;

    @FieldMapping(columnName = MediaStore.Video.VideoColumns.BOOKMARK, physicalType = FieldMapping.PhysicalType.Int)
    public int bookmark;
}
