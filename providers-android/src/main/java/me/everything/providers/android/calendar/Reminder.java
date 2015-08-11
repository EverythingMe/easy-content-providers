package me.everything.providers.android.calendar;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.CalendarContract.Reminders;

import me.everything.providers.core.Entity;
import me.everything.providers.core.EnumInt;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Reminder entity
 *
 * @author sromku
 * // @see http
 *      ://developer.android.com/reference/android/provider/CalendarContract.
 *      Reminders.html
 */
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

    @FieldMapping(columnName = Reminders.METHOD, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.EnumInt)
    public MethodType method;

    public enum MethodType implements EnumInt {
        DEFAULT(0),
        ALERT(1),
        EMAIL(2),
        SMS(3),
        ALARM(4);

        int val;

        MethodType(int val) {
            this.val = val;
        }

        public static MethodType fromVal(int val) {
            for (MethodType methodType : values()) {
                if (methodType.val == val) {
                    return methodType;
                }
            }
            return DEFAULT;
        }
    }

}
