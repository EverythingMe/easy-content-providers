package me.everything.providers.android.calendar;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.CalendarContract.Reminders;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;
import me.everything.providers.core.TableMapping;

/**
 * Reminder entity
 *
 * @author sromku
 * // @see http
 *      ://developer.android.com/reference/android/provider/CalendarContract.
 *      Reminders.html
 */
@TableMapping(tableName = "reminder")
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Reminder extends Entity {

    @IgnoreMapping
    public static Uri uri = Reminders.CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = Reminders.EVENT_ID, physicalType = FieldMapping.PhysicalType.Long)
    public long eventId;

    @FieldMapping(columnName = Reminders.MINUTES, physicalType = FieldMapping.PhysicalType.Int)
    public int minutes;

    @FieldMapping(columnName = Reminders.METHOD, physicalType = FieldMapping.PhysicalType.Int)
    public int method;

}
