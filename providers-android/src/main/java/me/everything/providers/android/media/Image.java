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
public class Image extends Entity {

    @IgnoreMapping
    public static Uri uriExternal = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    @IgnoreMapping
    public static Uri uriInternal = MediaStore.Images.Media.INTERNAL_CONTENT_URI;

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

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.DESCRIPTION, physicalType = FieldMapping.PhysicalType.String)
    public String description;

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.PICASA_ID, physicalType = FieldMapping.PhysicalType.String)
    public String picasaId;

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.IS_PRIVATE, logicalType = FieldMapping.LogicalType.Boolean, physicalType = FieldMapping.PhysicalType.Int)
    public boolean isPrivate;

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.LATITUDE, physicalType = FieldMapping.PhysicalType.Double)
    public double latitude;

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.LONGITUDE, physicalType = FieldMapping.PhysicalType.Double)
    public double longitude;

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.DATE_TAKEN, logicalType = FieldMapping.LogicalType.Long, physicalType = FieldMapping.PhysicalType.Int)
    public long dateTaken;

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.ORIENTATION, physicalType = FieldMapping.PhysicalType.Int)
    public int orientation;

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.MINI_THUMB_MAGIC, physicalType = FieldMapping.PhysicalType.Int)
    public int miniThumbMagic;

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.BUCKET_ID, physicalType = FieldMapping.PhysicalType.String)
    public String bucketId;

    @FieldMapping(columnName = MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String bucketDisplayName;
}
