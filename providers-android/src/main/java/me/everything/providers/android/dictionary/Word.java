package me.everything.providers.android.dictionary;

import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.UserDictionary;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Created by sromku on 8/6/15.
 */
public class Word extends Entity {

    @IgnoreMapping
    public static Uri uri = UserDictionary.Words.CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = UserDictionary.Words.WORD, physicalType = FieldMapping.PhysicalType.String)
    public String word;

    @FieldMapping(columnName = UserDictionary.Words.FREQUENCY, physicalType = FieldMapping.PhysicalType.Int)
    public int frequency;

    @FieldMapping(columnName = UserDictionary.Words.LOCALE, physicalType = FieldMapping.PhysicalType.String)
    public String locale;

    @FieldMapping(columnName = UserDictionary.Words.APP_ID, physicalType = FieldMapping.PhysicalType.Int)
    public int appId;

    @FieldMapping(columnName = UserDictionary.Words.SHORTCUT, physicalType = FieldMapping.PhysicalType.String)
    public String shortcut;
}
