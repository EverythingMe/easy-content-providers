package me.everything.providers.android.calendar;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.CalendarContract.Calendars;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Calendar entity
 *
 * @author sromku
 * // @see http://developer.android.com/reference/android/provider/CalendarContract.Calendars.html
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Calendar extends Entity {

    @IgnoreMapping
    public static Uri uri = Calendars.CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = Calendars.NAME, physicalType = FieldMapping.PhysicalType.String)
    public String name;

    @FieldMapping(columnName = Calendars.ALLOWED_REMINDERS, physicalType = FieldMapping.PhysicalType.String)
    public String allowedReminders;

    @FieldMapping(columnName = Calendars.CALENDAR_ACCESS_LEVEL, physicalType = FieldMapping.PhysicalType.Int)
    public int calendarAccessLevel;

    @FieldMapping(columnName = Calendars.CALENDAR_COLOR, canUpdate=true, physicalType = FieldMapping.PhysicalType.Int)
    public int calendarColor;

    @FieldMapping(columnName = Calendars.CALENDAR_DISPLAY_NAME, canUpdate=true, physicalType = FieldMapping.PhysicalType.String)
    public String displayName;

    @FieldMapping(columnName = Calendars.CALENDAR_TIME_ZONE, physicalType = FieldMapping.PhysicalType.String)
    public String calendarTimeZone;

    @FieldMapping(columnName = Calendars.CAN_MODIFY_TIME_ZONE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean canModifyTimeZone;

    @FieldMapping(columnName = Calendars.CAN_ORGANIZER_RESPOND, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean canOrginizerRespond;

    @FieldMapping(columnName = Calendars.MAX_REMINDERS, physicalType = FieldMapping.PhysicalType.Int)
    public int maxReminders;

    @FieldMapping(columnName = Calendars.OWNER_ACCOUNT, physicalType = FieldMapping.PhysicalType.String)
    public String ownerAccount;

    @FieldMapping(columnName = Calendars.SYNC_EVENTS, physicalType = FieldMapping.PhysicalType.Int)
    public int syncEvents;

    @FieldMapping(columnName = Calendars.VISIBLE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean visible;

    @FieldMapping(columnName = Calendars.ACCOUNT_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String accountName;

    @FieldMapping(columnName = Calendars.ACCOUNT_TYPE, physicalType = FieldMapping.PhysicalType.String)
    public String accountType;

    @FieldMapping(columnName = Calendars.CAN_PARTIALLY_UPDATE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean canPartiallyUpdate;

    @FieldMapping(columnName = Calendars.DELETED, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean deleted;

    @FieldMapping(columnName = Calendars.DIRTY, physicalType = FieldMapping.PhysicalType.Long)
    public long dirty;

    @FieldMapping(columnName = Calendars._SYNC_ID, physicalType = FieldMapping.PhysicalType.String)
    public String syncId;

    @FieldMapping(columnName = Calendars.CALENDAR_LOCATION, physicalType = FieldMapping.PhysicalType.String)
    public String location;

    @FieldMapping(columnName = Calendars.DEFAULT_SORT_ORDER, physicalType = FieldMapping.PhysicalType.String)
    @IgnoreMapping
    public String sortOrder;

}
