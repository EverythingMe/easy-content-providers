package me.everything.providers.android.calendar;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.CalendarContract.Events;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Event entity
 *
 * @author sromku
 * // @see http
 *      ://developer.android.com/reference/android/provider/CalendarContract.
 *      Events.html
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Event extends Entity {

    @IgnoreMapping
    public static Uri uri = Events.CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = Events.ALLOWED_REMINDERS, physicalType = FieldMapping.PhysicalType.String)
    public String allowedReminders;

    @FieldMapping(columnName = Events.CALENDAR_ACCESS_LEVEL, physicalType = FieldMapping.PhysicalType.Int)
    public int calendarAccessLevel;

    @FieldMapping(columnName = Events.CALENDAR_COLOR, physicalType = FieldMapping.PhysicalType.Int)
    public int calendarColor;

    @FieldMapping(columnName = Events.CALENDAR_DISPLAY_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String displayName;

    @FieldMapping(columnName = Events.CALENDAR_TIME_ZONE, physicalType = FieldMapping.PhysicalType.String)
    public String calendarTimeZone;

    @FieldMapping(columnName = Events.CAN_MODIFY_TIME_ZONE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean canModifyTimeZone;

    @FieldMapping(columnName = Events.CAN_ORGANIZER_RESPOND, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean canOrginizerRespond;

    @FieldMapping(columnName = Events.MAX_REMINDERS, physicalType = FieldMapping.PhysicalType.Int)
    public int maxReminders;

    @FieldMapping(columnName = Events.OWNER_ACCOUNT, physicalType = FieldMapping.PhysicalType.String)
    public String ownerAccount;

    @FieldMapping(columnName = Events.VISIBLE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean visible;

    @FieldMapping(columnName = Events.ACCOUNT_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String accountName;

    @FieldMapping(columnName = Events.ACCOUNT_TYPE, physicalType = FieldMapping.PhysicalType.String)
    public String accountType;

    @FieldMapping(columnName = Events.DELETED, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean deleted;

    @FieldMapping(columnName = Events._SYNC_ID, physicalType = FieldMapping.PhysicalType.String)
    public String syncId;

    /*
     * --- Event specific fields ---
     */
    @FieldMapping(columnName = Events.ACCESS_LEVEL, physicalType = FieldMapping.PhysicalType.Int)
    public int accessLevel;

    @FieldMapping(columnName = Events.ALL_DAY, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean allDay;

    @FieldMapping(columnName = Events.AVAILABILITY, physicalType = FieldMapping.PhysicalType.Int)
    public int availability;

    @FieldMapping(columnName = Events.CALENDAR_ID, physicalType = FieldMapping.PhysicalType.Long)
    public long calendarId;

    @FieldMapping(columnName = Events.DESCRIPTION, canUpdate=true, physicalType = FieldMapping.PhysicalType.String)
    public String description;

    @FieldMapping(columnName = Events.DTEND, physicalType = FieldMapping.PhysicalType.Long)
    public long dTend;

    @FieldMapping(columnName = Events.DTSTART, physicalType = FieldMapping.PhysicalType.Long)
    public long dTStart;

    @FieldMapping(columnName = Events.DURATION, physicalType = FieldMapping.PhysicalType.String)
    public String duration;

    @FieldMapping(columnName = Events.EVENT_COLOR, canUpdate=true, physicalType = FieldMapping.PhysicalType.Int)
    public int eventColor;

    @FieldMapping(columnName = Events.EVENT_END_TIMEZONE, physicalType = FieldMapping.PhysicalType.String)
    public String eventEndTimeZone;

    @FieldMapping(columnName = Events.EVENT_LOCATION, canUpdate=true, physicalType = FieldMapping.PhysicalType.String)
    public String eventLocation;

    @FieldMapping(columnName = Events.EVENT_TIMEZONE, physicalType = FieldMapping.PhysicalType.String)
    public String eventTimeZone;

    @FieldMapping(columnName = Events.EXDATE, physicalType = FieldMapping.PhysicalType.String)
    public String eventExDate;

    @FieldMapping(columnName = Events.EXRULE, physicalType = FieldMapping.PhysicalType.String)
    public String eventExRule;

    @FieldMapping(columnName = Events.GUESTS_CAN_INVITE_OTHERS, physicalType = FieldMapping.PhysicalType.Int)
    public int guestCanInviteOthers;

    @FieldMapping(columnName = Events.GUESTS_CAN_MODIFY, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean guestCanModify;

    @FieldMapping(columnName = Events.GUESTS_CAN_SEE_GUESTS, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean guestCanSeeQuests;

    @FieldMapping(columnName = Events.HAS_ALARM, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean hasAlarm;

    @FieldMapping(columnName = Events.HAS_ATTENDEE_DATA, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean hasAttendeeData;

    @FieldMapping(columnName = Events.HAS_EXTENDED_PROPERTIES, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean hasExtendedProperties;

    @FieldMapping(columnName = Events.LAST_DATE, physicalType = FieldMapping.PhysicalType.Long)
    public long lastDate;

    @FieldMapping(columnName = Events.ORGANIZER, physicalType = FieldMapping.PhysicalType.String)
    public String organizer;

    @FieldMapping(columnName = Events.ORIGINAL_ALL_DAY, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean originalAllDay;

    @FieldMapping(columnName = Events.ORIGINAL_ID, physicalType = FieldMapping.PhysicalType.String)
    public String originalId;

    @FieldMapping(columnName = Events.ORIGINAL_INSTANCE_TIME, physicalType = FieldMapping.PhysicalType.Long)
    public long originalInstanceTime;

    @FieldMapping(columnName = Events.ORIGINAL_SYNC_ID, physicalType = FieldMapping.PhysicalType.String)
    public String originalSyncId;

    @FieldMapping(columnName = Events.RDATE, physicalType = FieldMapping.PhysicalType.String)
    public String rDate;

    @FieldMapping(columnName = Events.RRULE, physicalType = FieldMapping.PhysicalType.String)
    public String rRule;

    @FieldMapping(columnName = Events.SELF_ATTENDEE_STATUS, physicalType = FieldMapping.PhysicalType.String)
    public String selfAttendeeStatus;

    @FieldMapping(columnName = Events.STATUS, physicalType = FieldMapping.PhysicalType.String)
    public String status;

    @FieldMapping(columnName = Events.TITLE, canUpdate=true, physicalType = FieldMapping.PhysicalType.String)
    public String title;

}
