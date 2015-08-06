package me.everything.providers.android.media;

import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import me.everything.providers.core.Entity;
import me.everything.providers.core.EnumInt;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Created by sromku
 */
public class File extends Entity {

    @IgnoreMapping
    public static Uri uriExternal = MediaStore.Files.getContentUri("external");

    @IgnoreMapping
    public static Uri uriInternal = MediaStore.Files.getContentUri("internal");

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

    @FieldMapping(columnName = MediaStore.Files.FileColumns.PARENT, physicalType = FieldMapping.PhysicalType.Int)
    public int parent;

    @FieldMapping(columnName = MediaStore.Files.FileColumns.MEDIA_TYPE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.EnumInt)
    public MediaType mediaType;

    public enum MediaType implements EnumInt {
        NONE(MediaStore.Files.FileColumns.MEDIA_TYPE_NONE),
        IMAGE(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE),
        AUDIO(MediaStore.Files.FileColumns.MEDIA_TYPE_AUDIO),
        VIDEO(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO),
        PLAYLIST(MediaStore.Files.FileColumns.MEDIA_TYPE_PLAYLIST);

        int val;

        MediaType(int val) {
            this.val = val;
        }

        public static MediaType fromVal(int val) {
            for (MediaType mediaType : values()) {
                if (mediaType.val == val) {
                    return mediaType;
                }
            }
            return null;
        }
    }

}
