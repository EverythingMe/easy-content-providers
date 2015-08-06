package me.everything.providers.android.browser;

import android.net.Uri;
import android.provider.Browser;
import android.provider.Browser.BookmarkColumns;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

public class Bookmark extends Entity {

    @IgnoreMapping
    public static Uri uri = Browser.BOOKMARKS_URI;

    @FieldMapping(columnName = BookmarkColumns.BOOKMARK, physicalType = FieldMapping.PhysicalType.Int)
    public int bookmark;

    @FieldMapping(columnName = BookmarkColumns.CREATED, physicalType = FieldMapping.PhysicalType.Long)
    public long created;

    @FieldMapping(columnName = BookmarkColumns.DATE, physicalType = FieldMapping.PhysicalType.Long)
    public long date;

    @FieldMapping(columnName = BookmarkColumns.FAVICON, physicalType = FieldMapping.PhysicalType.Blob)
    public byte[] favicon;

    @FieldMapping(columnName = BookmarkColumns.TITLE, physicalType = FieldMapping.PhysicalType.String)
    public String title;

    @FieldMapping(columnName = BookmarkColumns.URL, physicalType = FieldMapping.PhysicalType.String)
    public String url;

    @FieldMapping(columnName = BookmarkColumns.VISITS, physicalType = FieldMapping.PhysicalType.Int)
    public int visits;
}
