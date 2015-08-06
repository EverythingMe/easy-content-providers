package me.everything.providers.android.browser;

import android.net.Uri;
import android.provider.Browser;
import android.provider.Browser.SearchColumns;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

public class Search extends Entity {

    @IgnoreMapping
    public static Uri uri = Browser.SEARCHES_URI;

    @FieldMapping(columnName = SearchColumns.SEARCH, physicalType = FieldMapping.PhysicalType.String)
    public String search;

    @FieldMapping(columnName = SearchColumns.DATE, physicalType = FieldMapping.PhysicalType.Long)
    public long date;

}
