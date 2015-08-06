package me.everything.providers.sample.custom;

import android.net.Uri;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

public class Post extends Entity  {

    @IgnoreMapping
    public static Uri uri = PostsContentProvider.POSTS_URI;

    @FieldMapping(columnName = PostsTable.COLUMN_ID, physicalType = FieldMapping.PhysicalType.Int)
    public int _id;

    @FieldMapping(columnName = PostsTable.COLUMN_TITLE, physicalType = FieldMapping.PhysicalType.String)
    public String title;

    @FieldMapping(columnName = PostsTable.COLUMN_THUMBNAIL, physicalType = FieldMapping.PhysicalType.Blob, logicalType = FieldMapping.LogicalType.Boolean)
    public byte[] thumbnail;

    @FieldMapping(columnName = PostsTable.COLUMN_FROM_ID, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Long)
    public long fromId;

    @FieldMapping(columnName = PostsTable.COLUMN_IS_OWNER, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean isOwner;

    @FieldMapping(columnName = PostsTable.COLUMN_UPDATED_AT, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Long)
    public long updatedAt;


}
