package me.everything.providers.android.calendar;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.CalendarContract.Attendees;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Attendee entity
 *
 * @author sromku
 * // @see http
 *      ://developer.android.com/reference/android/provider/CalendarContract.
 *      Attendees.html
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Attendee extends Entity {

    @IgnoreMapping
    public static Uri uri = Attendees.CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = Attendees.EVENT_ID, physicalType = FieldMapping.PhysicalType.Long)
    public long eventId;

    @FieldMapping(columnName = Attendees.ATTENDEE_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String name;

    @FieldMapping(columnName = Attendees.ATTENDEE_EMAIL, physicalType = FieldMapping.PhysicalType.String)
    public String email;

    @FieldMapping(columnName = Attendees.ATTENDEE_RELATIONSHIP, physicalType = FieldMapping.PhysicalType.Int)
    public int relationship;

    @FieldMapping(columnName = Attendees.ATTENDEE_TYPE, physicalType = FieldMapping.PhysicalType.Int)
    public int type;

    @FieldMapping(columnName = Attendees.ATTENDEE_STATUS, physicalType = FieldMapping.PhysicalType.String)
    public String status;

}
